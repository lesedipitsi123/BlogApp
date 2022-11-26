package com.devbytes.app.blogapp.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.devbytes.app.blogapp.databinding.ItemListBlogBinding
import com.devbytes.app.blogapp.ui.stateholder.BlogItemUiState


class BlogItemListAdapter : ListAdapter<BlogItemUiState, BlogItemViewHolder>(BlogComparator) {
    init {
        hasStableIds()
    }

    private var itemClickListener: ((item: BlogItemUiState) -> Unit)? = null

    fun setItemClickListener(listener: (item: BlogItemUiState) -> Unit) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogItemViewHolder {
        return BlogItemViewHolder(
            ItemListBlogBinding.inflate(LayoutInflater.from(parent.context),parent, false )
        ) { position ->
            itemClickListener?.invoke(getItem(position))
        }
    }

    override fun onBindViewHolder(holder: BlogItemViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).hashCode().toLong()
    }
    companion object {
        private val BlogComparator = object : DiffUtil.ItemCallback<BlogItemUiState>(){
            override fun areItemsTheSame(
                oldItem: BlogItemUiState,
                newItem: BlogItemUiState
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: BlogItemUiState,
                newItem: BlogItemUiState
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}