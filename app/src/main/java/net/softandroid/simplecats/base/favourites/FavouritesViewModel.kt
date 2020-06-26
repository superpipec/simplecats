package net.softandroid.simplecats.base.favourites

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.softandroid.domain.common.mapBoth
import net.softandroid.domain.favourites.FavsCatsError
import net.softandroid.domain.favourites.FavsCatsUseCase
import net.softandroid.domain.favourites.entity.FavsCatsItem
import net.softandroid.simplecats.base.download.FileDownloader
import net.softandroid.simpleqrscanner.base.coroutine.DispatchersProvider
import net.softandroid.simpleqrscanner.base.viewmodel.CoroutineViewModel
import timber.log.Timber
import kotlin.properties.Delegates

class FavouritesViewModel(
    private val favsCatsUseCase: FavsCatsUseCase,
    private val fileDownloader: FileDownloader,
    private val dispatchersProvider: DispatchersProvider,
    errorHandlerFactory: FavouritesErrorHandlerFactory,

    val textErrorRes: MutableLiveData<Int> = MutableLiveData(),// i leave this properties mutable for speed developing
    val progress: MutableLiveData<Boolean> = MutableLiveData(),
    val listItems: MutableLiveData<List<FavsCatsItem>> = MutableLiveData()
) : CoroutineViewModel(dispatchersProvider) {

    private val errorHandler = errorHandlerFactory.create(this)
    var fileUrl: String by Delegates.notNull()
        private set

    fun setFileUrlString(url: String) {
        fileUrl = url
    }

    fun downloadFile() {
        fileDownloader.download(fileUrl)
    }

    fun getFavs() {
        progress.value = true
        launch {
            withContext(dispatchersProvider.io) {
                favsCatsUseCase.getAllCats()
            }.mapBoth(::onGetFavsSuccess, ::onGetFavsError)
        }
    }

    private fun onGetFavsSuccess(items: Collection<FavsCatsItem>) {
        clearStates()
        Timber.d("get favs success: $items")
        listItems.value = items.toList()
    }

    private fun onGetFavsError(error: FavsCatsError) {
        clearStates()
        errorHandler.handle(error)
    }

    private fun clearStates() {
        progress.value = false
        textErrorRes.value = null
    }

}