package com.devbytes.app.blogapp.data.source.dao

import androidx.room.*
import com.devbytes.app.blogapp.data.model.Blog

@Dao
interface BlogDao {
    @Query("SELECT * FROM blog_table WHERE id = :id")
    suspend fun get(id: Int): Blog

    @Query("SELECT * FROM blog_table ORDER BY title DESC")
    suspend fun get(): List<Blog>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun create(blogs: List<Blog>)

    @Update
    suspend fun update(blog: Blog)

    @Delete
    suspend fun delete(blog: Blog)
}