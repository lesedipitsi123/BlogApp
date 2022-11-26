package com.devbytes.app.blogapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devbytes.app.blogapp.R
import com.devbytes.app.blogapp.databinding.BlogScreenBinding
import com.devbytes.app.blogapp.ui.view.adapters.BlogItemListAdapter
import com.devbytes.app.blogapp.ui.viewmodel.BlogViewModel
import com.devbytes.app.blogapp.util.Constants.KEY_AUTHOR_ID
import com.devbytes.app.blogapp.util.Constants.KEY_AUTHOR_NAME
import com.devbytes.app.blogapp.util.Constants.KEY_BLOG_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogScreen : Fragment(), View.OnClickListener {
    private var _binding: BlogScreenBinding? = null
    private val binding get() = _binding!!
    private val blogViewModel by viewModels<BlogViewModel>()
    private val blogAdapter: BlogItemListAdapter = BlogItemListAdapter()
    private var authorId: Int = 0
    private lateinit var authorName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            authorId = it.getInt(KEY_AUTHOR_ID)
            authorName = it.getString(KEY_AUTHOR_NAME, "")
        }
    }

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
        initializeClickListeners()
        observeUiStateFlows()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add_blog -> {
                val args = bundleOf(KEY_AUTHOR_ID to authorId)
                findNavController().navigate(R.id.action_to_add_blog, args)
            }
        }
    }

    private fun initializeComponents() {
        with(binding) {
            appbarLayout.toolbar.title = getString(R.string.lbl_toolbar_blogs, authorName)
        }
    }

    private fun initializeClickListeners() {
        with(binding) {
            appbarLayout.toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            blogAdapter.setItemClickListener { item ->
                val args = bundleOf(KEY_BLOG_ID to item.id)
                findNavController().navigate(R.id.action_to_blog_view, args)
            }
            btnAddBlog.setOnClickListener(this@BlogScreen)
            emptyBlog.btnAddBlog.setOnClickListener(this@BlogScreen)
        }
    }

    private fun observeUiStateFlows() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            blogViewModel.getBlogUiState(authorId)
            blogViewModel.blogUiStateFlow.collect { uiState ->
                if (uiState.hasBlogs) {
                    binding.emptyBlog.root.visibility = View.GONE
                    blogAdapter.submitList(uiState.blogItems)
                    binding.recyclerviewBlogs.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = blogAdapter
                    }
                } else {
                    binding.emptyBlog.root.visibility = View.VISIBLE
                }
            }
        }
    }
}