package net.softandroid.simplecats.base.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.softandroid.domain.main.entity.GetCatsItem

class MainListAdapter(
    var items: List<GetCatsItem>,
    var onItemClickListener: ((GetCatsItem?) -> Unit)? = null,
    var onItemDownloadClickListener: ((String?) -> Unit)? = null
) : RecyclerView.Adapter<MainListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MainListViewHolder.create(parent.context, parent).apply {
            itemClickListener = onItemClickListener
            itemDownloadClickListener = onItemDownloadClickListener
        }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}