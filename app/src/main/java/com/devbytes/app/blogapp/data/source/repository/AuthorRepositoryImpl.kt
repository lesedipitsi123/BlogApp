package com.devbytes.app.blogapp.data.source.repository

import com.devbytes.app.blogapp.data.model.Author
import com.devbytes.app.blogapp.data.source.interfaces.AuthorDataSource
import com.devbytes.app.blogapp.data.source.interfaces.AuthorRepository
import com.devbytes.app.blogapp.dependency.annotation.LocalDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorRepositoryImpl @Inject constructor(
    @LocalDataSource private val dataSource: AuthorDataSource
) : AuthorRepository {
    override suspend fun get() = dataSource.get()

    override suspend fun get(id: Int) = dataSource.get(id)

    override suspend fun getAuthorsWithBlogs() = dataSource.getAuthorsWithBlogs()

    override suspend fun getAuthorWithBlogs(authorId: Int) = dataSource.getAuthorWithBlogs(authorId)

    override suspend fun create(author: Author) = dataSource.create(author)

    override suspend fun update(author: Author) = dataSource.update(author)

    override suspend fun delete(author: Author) = dataSource.delete(author)

    override suspend fun hasRecords() = dataSource.hasRecords()
}