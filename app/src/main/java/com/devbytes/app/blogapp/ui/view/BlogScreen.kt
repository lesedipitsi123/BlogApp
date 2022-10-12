package com.devbytes.app.blogapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.devbytes.app.blogapp.databinding.BlogScreenBinding
import com.devbytes.app.blogapp.ui.view.adapters.BlogItemListAdapter
import com.devbytes.app.blogapp.ui.viewmodel.BlogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogScreen : Fragment() {
    private var _binding: BlogScreenBinding? = null
    private val binding get() = _binding!!
    private val blogViewModel by viewModels<BlogViewModel>()
    private val blogAdapter: BlogItemListAdapter = BlogItemListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BlogScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponents()
    }

    private fun initializeComponents() {
        with(binding) {
            blogViewModel.getBlogUiState()
            lifecycleScope.launchWhenStarted {
                blogViewModel.blogUiStateFlow.collect { uiState ->
                    if (uiState.hasBlogs) {
                        emptyBlog.root.visibility = View.GONE
                        blogAdapter.submitList(uiState.blogItems)
                        recyclerviewBlogs.apply {
                            layoutManager = LinearLayoutManager(requireContext())
                            adapter = blogAdapter
                        }
                    } else {
                        emptyBlog.root.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}