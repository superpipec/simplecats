package net.softandroid.simplecats.base.main.controllers

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.softandroid.data.rest.api.LOAD_COUNT
import net.softandroid.domain.main.entity.GetCatsItem
import net.softandroid.simplecats.base.main.MainNavigator
import net.softandroid.simplecats.base.main.MainViewModel
import net.softandroid.simplecats.base.main.adapter.MainListAdapter
import net.softandroid.teststproject.app.view.ViewProvider


class MainListItemsController(
    lifecycle: Lifecycle,
    private val viewModel: MainViewModel,
    private val navigator: MainNavigator,
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
        viewModel.loadListener = {
            initOrReuseAdapter(viewModel.listItems.value, it)
        }
    }

    private fun initOrReuseAdapter(items: Collection<GetCatsItem>?, pos: Int) {
        if (items.isNullOrEmpty()) {
            return
        }
        val rv = recyclerViewProvider.view
        if (rv.adapter == null) {
            rv.layoutManager = LinearLayoutManager(rv.context, LinearLayoutManager.VERTICAL, false)
            val adapter = MainListAdapter(items.toList(),
                { catItem ->
                    catItem?.let { navigator.addToFavourites(it) }
                }, { url ->
                    url?.let { navigator.download(it) }
                }
            )
            rv.adapter = adapter
        } else {
            (rv.adapter as MainListAdapter).items = items.toList()
            rv.adapter?.notifyItemRangeInserted(pos, LOAD_COUNT)
        }
    }
}