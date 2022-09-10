package com.devbytes.app.blogapp.data.source.repository

import com.devbytes.app.blogapp.data.model.Blog

interface BlogRepository {
    suspend fun get(): List<Blog>
    suspend fun get(id: Int): Blog
    suspend fun create(blogs: List<Blog>)
    suspend fun update(blog: Blog)
    suspend fun delete(blog: Blog)
}