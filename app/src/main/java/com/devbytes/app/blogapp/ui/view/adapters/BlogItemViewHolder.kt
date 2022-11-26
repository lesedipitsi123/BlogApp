package com.devbytes.app.blogapp.ui.view.adapters

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.devbytes.app.blogapp.databinding.ItemListBlogBinding
import com.devbytes.app.blogapp.ui.stateholder.BlogItemUiState

class BlogItemViewHolder(
    private val binding: ItemListBlogBinding,
    private val itemClickListener: (position : Int) -> Unit
) : ViewHolder(binding.root) {

    init {
        binding.btnEdit.setOnClickListener { }
        binding.root.setOnClickListener {
            itemClickListener(adapterPosition)
        }
    }

    fun bind(item: BlogItemUiState) {
        with(binding) {
            textBlogTitle.text = item.title
            textBlogDescription.text = item.description
            imageBlog.setImageResource(item.imageRes)
        }
    }
}