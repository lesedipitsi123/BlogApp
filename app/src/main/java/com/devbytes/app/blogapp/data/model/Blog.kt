package com.devbytes.app.blogapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "blog_table")
data class Blog(
    val authorId: String,
    val title: String,
    val body: String,
    val comment: String,
    val dateCreated: Date
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}