package com.devbytes.app.blogapp.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "author_table")
data class Author(
    var name: String,
    @Ignore
    var blogs: List<Blog>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor() : this("", emptyList())
}