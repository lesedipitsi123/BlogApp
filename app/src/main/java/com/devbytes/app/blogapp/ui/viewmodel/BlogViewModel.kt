package com.devbytes.app.blogapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devbytes.app.blogapp.R
import com.devbytes.app.blogapp.data.model.Blog
import com.devbytes.app.blogapp.data.source.interfaces.AuthorRepository
import com.devbytes.app.blogapp.data.source.interfaces.BlogRepository
import com.devbytes.app.blogapp.ui.stateholder.BlogAddUiState
import com.devbytes.app.blogapp.ui.stateholder.BlogUiState
import com.devbytes.app.blogapp.util.toBlogUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel
@Inject constructor(
    private val repository: BlogRepository,
    private val authorRepository: AuthorRepository,
) : ViewModel() {
    private val _blogUiStateFlow = MutableStateFlow(BlogUiState())
    private val _blogAddUiStateFlow = MutableStateFlow(BlogAddUiState())
    val blogUiStateFlow get() = _blogUiStateFlow.asStateFlow()
    val blogAddUiState get() = _blogAddUiStateFlow.asStateFlow()

    fun getBlogAddData() = viewModelScope.launch {
        val authors = authorRepository.get()
        _blogAddUiStateFlow.update { it.copy(authors = authors.map { it.name }) }
    }

    fun getBlogUiState() = viewModelScope.launch {
        val blogs = repository.get()
        val hasBlogs = repository.hasRecords()
        _blogUiStateFlow.emit(blogs.toBlogUiState(hasBlogs, getRandomDrawable()))
    }

    fun getBlogItem(id: Int) = viewModelScope.launch {
        repository.get(id)
    }

    fun addNewBlog(blog: Blog) = viewModelScope.launch {
        repository.create(blog)
    }

    fun updateBlog(blog: Blog) = viewModelScope.launch {
        repository.update(blog)
    }

    fun removeBlog(blog: Blog) = viewModelScope.launch {
        repository.delete(blog)
    }

    private fun getRandomDrawable() : Int {
        val drawables = listOf(
            R.drawable.drawable_woman_doing_dumbbell_exercise,
            R.drawable.drawable_basketball_player,
            R.drawable.drawable_football_player_kick_ball,
            R.drawable.drawable_female_playing_volleyball,
            R.drawable.drawable_static_cycle)
        return drawables.random()
    }
}