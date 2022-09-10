package com.devbytes.app.blogapp.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devbytes.app.blogapp.data.model.Author
import com.devbytes.app.blogapp.data.model.Blog
import com.devbytes.app.blogapp.data.source.dao.AuthorDao
import com.devbytes.app.blogapp.data.source.dao.BlogDao
import com.devbytes.app.blogapp.util.Constants.DATABASE_VERSION

@Database(entities = [Author::class, Blog::class], version = DATABASE_VERSION, exportSchema = false)
abstract class BlogAppDB : RoomDatabase(){
    abstract fun authorDao(): AuthorDao
    abstract fun blogDao(): BlogDao
}