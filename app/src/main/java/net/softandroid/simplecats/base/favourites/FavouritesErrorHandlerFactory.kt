package net.softandroid.simplecats.base.favourites

class FavouritesErrorHandlerFactory {

    // used if error handler can handle multiple error types from different interactors
    fun create(viewModel: FavouritesViewModel) =
        FavouritesErrorHandler(viewModel)
}