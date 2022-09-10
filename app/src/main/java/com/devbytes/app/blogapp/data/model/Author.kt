package com.devbytes.app.blogapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author_table")
data class Author(
    val name: String,
    val blogs: List<Blog>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}