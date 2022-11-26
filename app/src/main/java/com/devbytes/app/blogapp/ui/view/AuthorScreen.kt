package com.devbytes.app.blogapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.devbytes.app.blogapp.R
import com.devbytes.app.blogapp.databinding.AuthorScreenBinding
import com.devbytes.app.blogapp.ui.view.adapters.AuthorItemListAdapter
import com.devbytes.app.blogapp.ui.viewmodel.AuthorViewModel
import com.devbytes.app.blogapp.util.Constants.KEY_AUTHOR_ID
import com.devbytes.app.blogapp.util.Constants.KEY_AUTHOR_NAME
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthorScreen : Fragment(), View.OnClickListener {
    private var _binding : AuthorScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<AuthorViewModel>()
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
        initializeClickListeners()
        observeUiStateFlows()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add_author -> {
                val authorAddModalSheet = AuthorAddModalSheet()
                authorAddModalSheet.show(childFragmentManager, AuthorAddModalSheet.TAG)
            }
        }
    }

    private fun initializeComponents() {
        with(binding) {
            appbarLayout.toolbar.title = getString(R.string.lbl_toolbar_authors)
        }
    }

    private fun initializeClickListeners() {
        with(binding) {
            emptyAuthor.btnAddAuthor.setOnClickListener(this@AuthorScreen)
            btnAddAuthor.setOnClickListener(this@AuthorScreen)
            authorAdapter.setItemClickListener { item ->
                val args = bundleOf( KEY_AUTHOR_ID to item.id, KEY_AUTHOR_NAME to item.authorName)
                findNavController().navigate(R.id.action_to_blogs, args)
            }
        }
    }

    private fun observeUiStateFlows() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getAuthorsUiState()
            viewModel.authorUiStateFlow.collect { uiState ->
                if (uiState.hasAuthors) {
                    authorAdapter.submitList(uiState.authors)
                    binding.emptyAuthor.root.visibility = View.GONE
                    binding.recyclerviewAuthors.apply {
                        layoutManager = GridLayoutManager(requireContext(), 2)
                        adapter = authorAdapter
                    }
                } else {
                    binding.emptyAuthor.root.visibility = View.VISIBLE
                }
            }
        }
    }
}