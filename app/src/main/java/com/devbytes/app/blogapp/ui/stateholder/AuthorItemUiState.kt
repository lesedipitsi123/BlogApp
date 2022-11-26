package com.devbytes.app.blogapp.ui.stateholder

import androidx.annotation.DrawableRes

data class AuthorItemUiState(
    val id: Int,
    @DrawableRes val imageRes: Int,
    val authorName: String,
    val blogsCount: Int,
)