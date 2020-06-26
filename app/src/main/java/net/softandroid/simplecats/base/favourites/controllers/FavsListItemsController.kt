package net.softandroid.simplecats.base.favourites.controllers

import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.softandroid.domain.favourites.entity.FavsCatsItem
import net.softandroid.simplecats.base.favourites.FavouritesViewModel
import net.softandroid.simplecats.base.favourites.FavsNavigator
import net.softandroid.simplecats.base.favourites.adapter.FavsListAdapter
import net.softandroid.teststproject.app.view.ViewProvider

class FavsListItemsController(
    lifecycle: Lifecycle,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: FavouritesViewModel,
    private val navigator: FavsNavigator,
    private val recyclerViewProvider: ViewProvider<RecyclerView>
) {

    init {
        lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onStart() {
                subscribeToChanges()
            }
        })
    }

    private fun subscribeToChanges() {
        viewModel.listItems.observe(lifecycleOwner, Observer {
            initOrReuseAdapter(it)
        })
    }

    private fun initOrReuseAdapter(items: List<FavsCatsItem>?) {
        if (items.isNullOrEmpty()) {
            return
        }
        val rv = recyclerViewProvider.view
        if (rv.adapter == null) {
            rv.layoutManager = LinearLayoutManager(rv.context, LinearLayoutManager.VERTICAL, false)
            val adapter = FavsListAdapter(
                items
            ) { url ->
                url?.let { navigator.download(it) }
            }
            rv.adapter = adapter
        } else {
            (rv.adapter as FavsListAdapter).items = items
            rv.adapter?.notifyDataSetChanged()
        }
    }
}