package net.softandroid.simplecats.ui.main

import net.softandroid.domain.main.entity.GetCatsItem
import net.softandroid.simplecats.base.main.MainNavigator
import net.softandroid.simplecats.base.main.MainViewModel
import net.softandroid.simplecats.ui.favourites.FavouritesActivity

class MainActivityNavigator(
    private val activity: MainActivity,
    private val viewModel: MainViewModel
) : MainNavigator {

    override fun toFavourites() {
        val intent = FavouritesActivity.buildIntent(activity)
        activity.startActivity(intent)
    }

    override fun addToFavourites(getCatsItem: GetCatsItem) {
        viewModel.addToFavs(getCatsItem)
    }

    override fun download(url: String) {
        viewModel.setFileUrlString(url)
        activity.downloadImage()
    }
}