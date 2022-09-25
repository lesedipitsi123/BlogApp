package com.devbytes.app.blogapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.devbytes.app.blogapp.data.source.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BlogViewModel
@Inject constructor(private val repository: BlogRepository) : ViewModel()