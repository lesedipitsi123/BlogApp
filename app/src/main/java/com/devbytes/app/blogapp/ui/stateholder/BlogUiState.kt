package com.devbytes.app.blogapp.ui.stateholder

data class BlogUiState(
    val hasBlogs: Boolean = false,
    val blogItems: List<BlogItemUiState> = listOf()
)