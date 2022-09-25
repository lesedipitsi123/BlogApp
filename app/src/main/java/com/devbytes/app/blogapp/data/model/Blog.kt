package com.devbytes.app.blogapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blog_table")
data class Blog(
    var authorId: Int,
    var title: String,
    var description: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor() : this(0, "", "")
}