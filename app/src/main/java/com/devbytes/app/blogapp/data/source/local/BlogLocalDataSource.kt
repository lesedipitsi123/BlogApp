package com.devbytes.app.blogapp.data.source.local

import com.devbytes.app.blogapp.data.model.Blog
import com.devbytes.app.blogapp.data.source.dao.BlogDao
import com.devbytes.app.blogapp.data.source.interfaces.BlogDataSource
import javax.inject.Inject

class BlogLocalDataSource @Inject constructor(
    private val dao: BlogDao
) : BlogDataSource {
    override suspend fun get() = dao.get()

    override suspend fun get(id: Int) = dao.get(id)

    override suspend fun getByAuthorId(authorId: Int) = dao.getByAuthorId(authorId)

    override suspend fun create(blog: Blog) = dao.create(blog)

    override suspend fun create(blogs: List<Blog>) = dao.create(blogs)

    override suspend fun update(blog: Blog) = dao.update(blog)

    override suspend fun delete(blog: Blog) = dao.delete(blog)

    override suspend fun hasRecords() = dao.hasRecords()
}