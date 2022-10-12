package com.devbytes.app.blogapp.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.devbytes.app.blogapp.databinding.ItemListAuthorBinding
import com.devbytes.app.blogapp.ui.stateholder.AuthorItemUiState

class AuthorItemListAdapter : ListAdapter<AuthorItemUiState, AuthorItemViewHolder>(AuthorComparator) {
    init {
        hasStableIds()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorItemViewHolder {
        return AuthorItemViewHolder(
            parent.context,
            ItemListAuthorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AuthorItemViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).hashCode().toLong()
    }

    companion object {
        private val AuthorComparator = object : DiffUtil.ItemCallback<AuthorItemUiState>() {
            override fun areItemsTheSame(
                oldItem: AuthorItemUiState,
                newItem: AuthorItemUiState
            ): Boolean {
                return oldItem.authorName == newItem.authorName
            }

            override fun areContentsTheSame(
                oldItem: AuthorItemUiState,
                newItem: AuthorItemUiState
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}