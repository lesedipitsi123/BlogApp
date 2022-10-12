package com.devbytes.app.blogapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author_table")
data class Author(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
)