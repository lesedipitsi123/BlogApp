package com.devbytes.app.blogapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.devbytes.app.blogapp.R
import com.devbytes.app.blogapp.databinding.AuthorScreenBinding
import com.devbytes.app.blogapp.ui.view.adapters.AuthorItemListAdapter
import com.devbytes.app.blogapp.ui.viewmodel.AuthorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorScreen : Fragment() {
    private var _binding : AuthorScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<AuthorViewModel>()
    private val authorAdapter: AuthorItemListAdapter = AuthorItemListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  AuthorScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponents()
    }

    private fun initializeComponents() {
        with(binding) {
            appbarLayout.toolbar.title = getString(R.string.lbl_toolbar_authors)
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.getAuthorUiState()
                viewModel.authorUiStateFlow.collect { uiState ->
                    if (uiState.hasAuthors) {
                        authorAdapter.submitList(uiState.authors)
                        emptyAuthor.root.visibility = View.GONE
                        recyclerviewAuthors.apply {
                            layoutManager = GridLayoutManager(requireContext(), 2)
                            adapter = authorAdapter
                        }
                    } else {
                        emptyAuthor.root.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}