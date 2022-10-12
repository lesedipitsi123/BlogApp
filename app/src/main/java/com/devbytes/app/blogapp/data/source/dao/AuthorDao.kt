package com.devbytes.app.blogapp.data.source.dao

import androidx.room.*
import com.devbytes.app.blogapp.data.model.Author
import com.devbytes.app.blogapp.data.model.AuthorWithBlogs

@Dao
interface AuthorDao {
    @Query("SELECT * FROM author_table WHERE id = :id")
    suspend fun get(id: Int): Author

    @Query("SELECT * FROM author_table")
    suspend fun get(): List<Author>

    @Transaction
    @Query("SELECT * FROM author_table")
    fun getAuthorsWithBlogs(): List<AuthorWithBlogs>

    @Transaction
    @Query("SELECT * FROM author_table WHERE id = :authorId")
    fun getAuthorWithBlogs(authorId: Int): AuthorWithBlogs

    @Query("SELECT EXISTS(SELECT * FROM author_table)")
    suspend fun hasRecords(): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun create(entity: Author)

    @Update
    suspend fun update(entity: Author)

    @Delete
    suspend fun delete(vararg entity: Author)
}