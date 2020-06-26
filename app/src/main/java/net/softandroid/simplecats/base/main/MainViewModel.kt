package net.softandroid.simplecats.base.main

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import net.softandroid.data.database.wrapper.DbFavsCatsItemWrapper
import net.softandroid.domain.common.mapBoth
import net.softandroid.domain.favourites.FavsCatsError
import net.softandroid.domain.favourites.FavsCatsUseCase
import net.softandroid.domain.main.GetCatsListError
import net.softandroid.domain.main.GetCatsListUseCase
import net.softandroid.domain.main.entity.GetCatsItem
import net.softandroid.simplecats.base.download.FileDownloader
import net.softandroid.simpleqrscanner.base.coroutine.DispatchersProvider
import net.softandroid.simpleqrscanner.base.viewmodel.CoroutineViewModel
import timber.log.Timber
import kotlin.properties.Delegates

class MainViewModel(
    private val catsListUseCase: GetCatsListUseCase,
    private val favsCatsUseCase: FavsCatsUseCase,
    private val wrapper: DbFavsCatsItemWrapper,
    private val fileDownloader: FileDownloader,
    dispatchersProvider: DispatchersProvider,
    errorHandlerFactory: MainErrorHandlerFactory,

    val textErrorRes: MutableLiveData<Int> = MutableLiveData(),// i leave this properties mutable for speed developing
    val progress: MutableLiveData<Boolean> = MutableLiveData(),
    val listItems: MutableLiveData<MutableList<GetCatsItem>> = MutableLiveData()
) : CoroutineViewModel(dispatchersProvider) {

    init {
        listItems.value = mutableListOf()
    }

    private val errorHandler = errorHandlerFactory.create(this)
    var loadListener: ((Int) -> Unit)? = null
    var saveListener: (() -> Unit)? = null
    var errorSaveListener: (() -> Unit)? = null

    var fileUrl: String by Delegates.notNull()
        private set

    fun getItems() {
        progress.value = true
        launch {
            // use null due result always random
            catsListUseCase.getCatsListImages(null)
                .mapBoth(::onGetListImagesSuccess, ::onGetListImagesError)
        }
    }

    fun setFileUrlString(url: String) {
        fileUrl = url
    }

    fun downloadFile() {
        fileDownloader.download(fileUrl)
    }

    fun addToFavs(item: GetCatsItem) {
        launch {
            favsCatsUseCase.saveCat(wrapper.toDbFavsItem(item))
                .mapBoth(::onSaveFavsSuccess, ::onSaveFavsError)
        }
    }

    private fun onSaveFavsSuccess(unit: Unit) {
        saveListener?.invoke()
    }

    private fun onSaveFavsError(error: FavsCatsError) {
        // stub error handler
        errorSaveListener?.invoke()
    }

    private fun onGetListImagesSuccess(items: Collection<GetCatsItem>) {
        clearStates()
        listItems.value?.addAll(items)
        listItems.value?.let {
            loadListener?.invoke(it.size)
        }
    }

    private fun onGetListImagesError(error: GetCatsListError) {
        clearStates()
        Timber.d("GET IMAGES ERROR: $error")
        errorHandler.handle(error)
    }

    private fun clearStates() {
        progress.value = false
        textErrorRes.value = null
    }

}