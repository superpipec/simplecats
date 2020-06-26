package net.softandroid.simplecats.ui.favourites

import net.softandroid.simplecats.base.favourites.FavouritesViewModel
import net.softandroid.simplecats.base.favourites.FavsNavigator

class FavouritesActivityNavigator(
    private val activity: FavouritesActivity,
    private val viewModel: FavouritesViewModel
) : FavsNavigator {

    override fun download(url: String) {
        viewModel.setFileUrlString(url)
        activity.downloadImage()
    }
}