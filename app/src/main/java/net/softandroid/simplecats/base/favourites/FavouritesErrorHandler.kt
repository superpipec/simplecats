package net.softandroid.simplecats.base.favourites

import net.softandroid.domain.favourites.FavsCatsError
import net.softandroid.simplecats.R

class FavouritesErrorHandler(
    private val vm: FavouritesViewModel
) {

    fun handle(error: FavsCatsError) = when (error) {
        FavsCatsError.StubError -> vm.textErrorRes.value =
            R.string.common_network_error
    }
}