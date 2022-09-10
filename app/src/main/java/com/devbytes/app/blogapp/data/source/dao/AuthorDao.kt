package com.devbytes.app.blogapp.data.source.dao

import androidx.room.*
import com.devbytes.app.blogapp.data.model.Author

@Dao
interface AuthorDao {
    @Query("SELECT * FROM author_table WHERE id = :id")
    suspend fun get(id: Int): Author

    @Query("SELECT * FROM author_table")
    suspend fun get(): List<Author>

    @Query("SELECT EXISTS(SELECT * FROM author_table)")
    suspend fun hasRecords(): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun create(entity: Author)

    @Update
    suspend fun update(entity: Author)

    @Delete
    suspend fun delete(vararg entity: Author)
}