package com.devbytes.app.blogapp.data.source.dao

import androidx.room.*
import com.devbytes.app.blogapp.data.model.Blog

@Dao
interface BlogDao {
    @Query("SELECT * FROM blog_table ORDER BY title DESC")
    suspend fun get(): List<Blog>

    @Query("SELECT * FROM blog_table WHERE id = :id")
    suspend fun get(id: Int): Blog

    @Query("SELECT * FROM blog_table WHERE authorId = :authorId")
    suspend fun getByAuthorId(authorId: Int): List<Blog>

    @Query("SELECT EXISTS(SELECT * FROM blog_table)")
    suspend fun hasRecords(): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun create(blogs: List<Blog>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun create(blog: Blog)

    @Update
    suspend fun update(blog: Blog)

    @Delete
    suspend fun delete(blog: Blog)
}