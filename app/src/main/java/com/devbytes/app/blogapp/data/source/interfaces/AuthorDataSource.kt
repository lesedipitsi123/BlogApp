package com.devbytes.app.blogapp.data.source.interfaces

import com.devbytes.app.blogapp.data.model.Author

interface AuthorDataSource {
    suspend fun get(id: Int) : Author
    suspend fun create(author: Author)
    suspend fun update(author: Author)
    suspend fun delete(author: Author)
}