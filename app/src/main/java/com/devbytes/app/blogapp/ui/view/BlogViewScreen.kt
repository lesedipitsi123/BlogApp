package com.devbytes.app.blogapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.devbytes.app.blogapp.R
import com.devbytes.app.blogapp.databinding.BlogViewScreenBinding
import com.devbytes.app.blogapp.ui.viewmodel.BlogViewModel
import com.devbytes.app.blogapp.util.Constants.KEY_BLOG_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogViewScreen : Fragment(), View.OnClickListener {
    private var _binding: BlogViewScreenBinding? = null
    private val binding get() = _binding!!
    private val blogViewModel by viewModels<BlogViewModel>()
    private var blogId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            blogId = it.getInt(KEY_BLOG_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BlogViewScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeClickListeners()
        observeUiStateFlows()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_edit -> {

            }
        }
    }

    private fun initializeClickListeners() {
        with(binding) {
            appbarLayout.toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            btnEdit.setOnClickListener(this@BlogViewScreen)
        }
    }

    private fun observeUiStateFlows() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            blogViewModel.getBlogItem(blogId)
            blogViewModel.blogItemUiStateFlow.collect { item ->
                binding.appbarLayout.toolbar.title = item.title
                binding.textDescription.text = item.description
                binding.imageResource.setImageResource(item.imageRes)
            }
        }
    }
}