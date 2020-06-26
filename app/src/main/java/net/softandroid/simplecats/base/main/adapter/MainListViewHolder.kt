package net.softandroid.simplecats.base.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import net.softandroid.domain.main.entity.GetCatsItem
import net.softandroid.simplecats.R
import net.softandroid.simplecats.databinding.MainListItemBinding

class MainListViewHolder private constructor(
    private val binding: MainListItemBinding,
    var itemClickListener: ((GetCatsItem?) -> Unit)? = null,
    var itemDownloadClickListener: ((String?) -> Unit)? = null
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.mainListFav.setOnClickListener {
            itemClickListener?.invoke(binding.item)
        }
        binding.mainListDownload.setOnClickListener {
            itemDownloadClickListener?.invoke(binding.item!!.url)
        }

    }

    fun bind(item: GetCatsItem) {
        binding.item = item
    }

    companion object {
        fun create(
            context: Context,
            viewGroup: ViewGroup?
        ) = MainListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.main_list_item,
                viewGroup,
                false
            )
        )
    }
}