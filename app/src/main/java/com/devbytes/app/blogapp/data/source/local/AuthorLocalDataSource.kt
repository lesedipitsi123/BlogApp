package com.devbytes.app.blogapp.data.source.local

import com.devbytes.app.blogapp.data.model.Author
import com.devbytes.app.blogapp.data.source.dao.AuthorDao
import com.devbytes.app.blogapp.data.source.interfaces.AuthorDataSource
import javax.inject.Inject

class AuthorLocalDataSource @Inject constructor(
    private val dao: AuthorDao
) : AuthorDataSource {
    override suspend fun get(id: Int) = dao.get(id)

    override suspend fun create(author: Author) = dao.create(author)

    override suspend fun update(author: Author) = dao.update(author)

    override suspend fun delete(author: Author) = dao.delete(author)
}