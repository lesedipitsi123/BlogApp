package com.devbytes.app.blogapp.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class AuthorWithBlogs(
    @Embedded
    val author: Author,
    @Relation(
        parentColumn = "id",
        entityColumn = "authorId"
    )
    val blogs: List<Blog>
)