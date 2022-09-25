package com.devbytes.app.blogapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.devbytes.app.blogapp.data.source.repository.AuthorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorViewModel @Inject
constructor(private val repository: AuthorRepository) : ViewModel()