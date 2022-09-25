package com.devbytes.app.blogapp.dependency

import android.content.Context
import androidx.room.Room
import com.devbytes.app.blogapp.data.source.dao.AuthorDao
import com.devbytes.app.blogapp.data.source.dao.BlogDao
import com.devbytes.app.blogapp.data.source.db.BlogAppDB
import com.devbytes.app.blogapp.data.source.interfaces.AuthorDataSource
import com.devbytes.app.blogapp.data.source.interfaces.BlogDataSource
import com.devbytes.app.blogapp.data.source.local.AuthorLocalDataSource
import com.devbytes.app.blogapp.data.source.local.BlogLocalDataSource
import com.devbytes.app.blogapp.data.source.repository.AuthorRepository
import com.devbytes.app.blogapp.data.source.repository.AuthorRepositoryImpl
import com.devbytes.app.blogapp.data.source.repository.BlogRepository
import com.devbytes.app.blogapp.data.source.repository.BlogRepositoryImpl
import com.devbytes.app.blogapp.dependency.annotation.LocalDataSource
import com.devbytes.app.blogapp.util.Constants.DATABASE_NAME
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModules {

    @Binds
    @LocalDataSource
    abstract fun bindAuthorDataSource(source: AuthorLocalDataSource): AuthorDataSource

    @Binds
    @LocalDataSource
    abstract fun bindBlogDataSource(source: BlogLocalDataSource): BlogDataSource

    @Binds
    abstract fun bindAuthorRepository(repository: AuthorRepositoryImpl): AuthorRepository

    @Binds
    abstract fun bindBlogRepository(repository: BlogRepositoryImpl): BlogRepository

    @Module
    @InstallIn(SingletonComponent::class)
    object Providers {
        @Provides
        fun provideBlogDao(db : BlogAppDB) : BlogDao {
            return db.blogDao()
        }

        @Provides
        fun provideAuthorDao(db : BlogAppDB) : AuthorDao {
            return db.authorDao()
        }

        @Provides
        @Singleton
        fun provideBlogAppDB(@ApplicationContext context: Context) : BlogAppDB {
            return Room.databaseBuilder(context, BlogAppDB::class.java, DATABASE_NAME).build()
        }
    }
}
