package com.devbytes.app.blogapp.ui.stateholder

import androidx.annotation.DrawableRes

data class AuthorItemUiState(
    @DrawableRes val imageRes: Int,
    val authorName: String,
    val blogsCount: Int,
)