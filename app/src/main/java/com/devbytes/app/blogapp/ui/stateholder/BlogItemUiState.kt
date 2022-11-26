package com.devbytes.app.blogapp.ui.stateholder

import androidx.annotation.DrawableRes

data class BlogItemUiState(
    val id: Int = 0,
    @DrawableRes val imageRes: Int = 0,
    val title: String = "",
    val description: String = "",
)