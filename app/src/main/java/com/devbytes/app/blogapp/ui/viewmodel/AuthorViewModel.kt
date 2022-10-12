package com.devbytes.app.blogapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devbytes.app.blogapp.R
import com.devbytes.app.blogapp.data.model.Author
import com.devbytes.app.blogapp.data.source.interfaces.AuthorRepository
import com.devbytes.app.blogapp.ui.stateholder.AuthorAddUiState
import com.devbytes.app.blogapp.ui.stateholder.AuthorUiState
import com.devbytes.app.blogapp.util.toAuthorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorViewModel
@Inject constructor(
    private val repository: AuthorRepository
) : ViewModel() {
    private val _authorAddUiState = MutableStateFlow(AuthorAddUiState())
    private val _authorUiStateFlow = MutableStateFlow(AuthorUiState())
    val authorUiStateFlow get() = _authorUiStateFlow.asStateFlow()

    fun getAuthorUiState() = viewModelScope.launch {
        val authorsWithBlogs = repository.getAuthorsWithBlogs()
        val hasAuthors = repository.hasRecords()
        _authorUiStateFlow.emit(authorsWithBlogs.toAuthorUiState(hasAuthors, getRandomDrawable()))
    }

    fun addNewAuthor(name: String) = viewModelScope.launch {
        repository.create(Author(name = name))
    }

    private fun getRandomDrawable(): Int {
        val drawables = listOf(
            R.drawable.drawable_woman_doing_dumbbell_exercise,
            R.drawable.drawable_basketball_player,
            R.drawable.drawable_football_player_kick_ball,
            R.drawable.drawable_female_playing_volleyball,
            R.drawable.drawable_static_cycle
        )
        return drawables.random()
    }
}