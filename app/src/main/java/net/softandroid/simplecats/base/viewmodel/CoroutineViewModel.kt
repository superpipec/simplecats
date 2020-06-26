package net.softandroid.simpleqrscanner.base.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import net.softandroid.simpleqrscanner.base.coroutine.DispatchersProvider
import kotlin.coroutines.CoroutineContext


/**
 * Common [ViewModel] with coroutine scope.
 * Creates coroutine scope implementation and manages view model lifecycle.
 * */
open class CoroutineViewModel(
    dispatchers: DispatchersProvider
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext =
        dispatchers.main + SupervisorJob()

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }
}
