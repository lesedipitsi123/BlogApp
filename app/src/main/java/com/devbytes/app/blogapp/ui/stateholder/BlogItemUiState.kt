package com.devbytes.app.blogapp.ui.stateholder

import androidx.annotation.DrawableRes

data class BlogItemUiState(
    val id: Int,
    @DrawableRes val imageRes: Int,
    val title: String,
    val description: String,
)