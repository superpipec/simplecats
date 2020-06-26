package net.softandroid.simplecats.base.main

class MainErrorHandlerFactory {

    // used if error handler can handle multiple error types from different interactors
    fun create(viewModel: MainViewModel) =
        MainErrorHandler(viewModel)
}