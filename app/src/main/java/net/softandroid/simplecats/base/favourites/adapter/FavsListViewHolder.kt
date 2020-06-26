package net.softandroid.simplecats.base.favourites.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import net.softandroid.domain.favourites.entity.FavsCatsItem
import net.softandroid.simplecats.R
import net.softandroid.simplecats.databinding.FavsListItemBinding

class FavsListViewHolder private constructor(
    private val binding: FavsListItemBinding,
    var itemDownloadClickListener: ((String?) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.favsListDownload.setOnClickListener {
            itemDownloadClickListener?.invoke(binding.item!!.url)
        }

    }

    fun bind(item: FavsCatsItem) {
        binding.item = item
    }

    companion object {
        fun create(
            context: Context,
            viewGroup: ViewGroup?
        ) = FavsListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.favs_list_item,
                viewGroup,
                false
            )
        )
    }
}