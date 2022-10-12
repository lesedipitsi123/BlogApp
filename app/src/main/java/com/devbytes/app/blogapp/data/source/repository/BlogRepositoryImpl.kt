package com.devbytes.app.blogapp.data.source.repository

import com.devbytes.app.blogapp.data.model.Blog
import com.devbytes.app.blogapp.data.source.interfaces.BlogDataSource
import com.devbytes.app.blogapp.data.source.interfaces.BlogRepository
import com.devbytes.app.blogapp.dependency.annotation.LocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogRepositoryImpl @Inject constructor(
    @LocalDataSource private val dataSource: BlogDataSource
) : BlogRepository {
    override suspend fun get() = dataSource.get()

    override suspend fun get(id: Int) = dataSource.get(id)
    override suspend fun create(blog: Blog) = dataSource.create(blog)

    override suspend fun create(blogs: List<Blog>) = dataSource.create(blogs)

    override suspend fun update(blog: Blog) = dataSource.update(blog)

    override suspend fun delete(blog: Blog) = dataSource.delete(blog)

    override suspend fun hasRecords() = dataSource.hasRecords()
}