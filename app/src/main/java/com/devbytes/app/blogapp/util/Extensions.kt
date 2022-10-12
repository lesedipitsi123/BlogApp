package com.devbytes.app.blogapp.util

import androidx.annotation.DrawableRes
import com.devbytes.app.blogapp.data.model.AuthorWithBlogs
import com.devbytes.app.blogapp.data.model.Blog
import com.devbytes.app.blogapp.ui.stateholder.AuthorItemUiState
import com.devbytes.app.blogapp.ui.stateholder.AuthorUiState
import com.devbytes.app.blogapp.ui.stateholder.BlogItemUiState
import com.devbytes.app.blogapp.ui.stateholder.BlogUiState

fun List<AuthorWithBlogs>.toAuthorUiState(hasAuthors:Boolean, @DrawableRes image: Int): AuthorUiState {
    return AuthorUiState(hasAuthors = hasAuthors, authors = this.map { it.toAuthorItemUiState(image) })
}

fun List<Blog>.toBlogUiState(hasBlogs: Boolean, @DrawableRes image: Int) : BlogUiState {
    return BlogUiState(hasBlogs = hasBlogs, blogItems = this.map { it.toBlogItemUiState(image) })
}

private fun Blog.toBlogItemUiState(@DrawableRes image: Int) : BlogItemUiState {
    return BlogItemUiState(
        id = this.id,
        imageRes = image,
        title = this.title,
        description =  this.description
    )
}

private fun AuthorWithBlogs.toAuthorItemUiState(@DrawableRes image: Int): AuthorItemUiState {
    return AuthorItemUiState(
        imageRes = image,
        authorName = this.author.name,
        blogsCount =  this.blogs.size
    )
}