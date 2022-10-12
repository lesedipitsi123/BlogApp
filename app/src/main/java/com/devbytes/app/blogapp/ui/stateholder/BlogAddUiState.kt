package com.devbytes.app.blogapp.ui.stateholder

data class BlogAddUiState(
    val title: String = "",
    val description: String = "",
    val authors: List<String> = listOf(),
)