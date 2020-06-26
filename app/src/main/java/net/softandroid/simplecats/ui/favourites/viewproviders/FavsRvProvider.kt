package net.softandroid.teststproject.ui.main.viewproviders

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_favourites.*
import net.softandroid.simplecats.ui.favourites.FavouritesActivity
import net.softandroid.teststproject.app.view.ViewProvider

class FavsRvProvider(
    private val activity: FavouritesActivity
) : ViewProvider<RecyclerView> {
    override val view: RecyclerView
        get() = activity.favsRvItems
}