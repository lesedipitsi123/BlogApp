package com.devbytes.app.blogapp.ui.stateholder

data class AuthorUiState(
    val hasAuthors: Boolean = false,
    val authors: List<AuthorItemUiState> = listOf()
)