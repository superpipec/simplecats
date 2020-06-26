package net.softandroid.simplecats.base.favourites.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.softandroid.domain.favourites.entity.FavsCatsItem

class FavsListAdapter(
    var items: List<FavsCatsItem>,
    var onItemDownloadClickListener: ((String?) -> Unit)? = null
) : RecyclerView.Adapter<FavsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavsListViewHolder.create(parent.context, parent).apply {
            itemDownloadClickListener = onItemDownloadClickListener
        }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FavsListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}