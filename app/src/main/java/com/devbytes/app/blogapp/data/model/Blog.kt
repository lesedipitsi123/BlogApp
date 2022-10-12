package com.devbytes.app.blogapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blog_table")
data class Blog(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val authorId: Int,
    val title: String,
    val description: String
)