package com.devbytes.app.blogapp.ui.view.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.devbytes.app.blogapp.R
import com.devbytes.app.blogapp.databinding.ItemListAuthorBinding
import com.devbytes.app.blogapp.ui.stateholder.AuthorItemUiState

class AuthorItemViewHolder(
    private val context: Context,
    private val binding: ItemListAuthorBinding
) : ViewHolder(binding.root) {

    init {
        binding.btnEdit.setOnClickListener {

        }
    }

    fun bind(item: AuthorItemUiState) {
        with(binding) {
            textAuthorName.text = item.authorName
            textBlogsCount.text = context.getString(R.string.lbl_text_blogs, item.blogsCount)
        }
    }
}