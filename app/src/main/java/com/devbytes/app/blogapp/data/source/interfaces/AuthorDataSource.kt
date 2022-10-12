package com.devbytes.app.blogapp.data.source.interfaces

import com.devbytes.app.blogapp.data.model.Author
import com.devbytes.app.blogapp.data.model.AuthorWithBlogs

interface AuthorDataSource {
    suspend fun get(): List<Author>
    suspend fun get(id: Int) : Author
    suspend fun getAuthorsWithBlogs(): List<AuthorWithBlogs>
    suspend fun getAuthorWithBlogs(authorId: Int): AuthorWithBlogs
    suspend fun create(author: Author)
    suspend fun update(author: Author)
    suspend fun delete(author: Author)
    suspend fun hasRecords(): Boolean
}