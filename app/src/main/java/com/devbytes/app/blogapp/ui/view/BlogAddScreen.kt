package com.devbytes.app.blogapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.devbytes.app.blogapp.R
import com.devbytes.app.blogapp.data.model.Blog
import com.devbytes.app.blogapp.databinding.BlogAddScreenBinding
import com.devbytes.app.blogapp.ui.viewmodel.AuthorViewModel
import com.devbytes.app.blogapp.ui.viewmodel.BlogViewModel
import com.devbytes.app.blogapp.util.Constants.KEY_AUTHOR_ID
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogAddScreen : Fragment(), View.OnClickListener {
    private var _binding : BlogAddScreenBinding? = null
    private val binding get() = _binding!!
    private val blogViewModel by viewModels<BlogViewModel>()
    private val authorViewModel by viewModels<AuthorViewModel>()
    private var authorId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            authorId = it.getInt(KEY_AUTHOR_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  BlogAddScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponents()
        initializeClickListeners()
        observeUiStateFlows()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_done -> {
                addBlog()
            }
        }
    }

    private fun initializeComponents() {
        with(binding) {
            appbarLayout.toolbar.title = getString(R.string.lbl_toolbar_add_blogs)
        }
    }

    private fun initializeClickListeners() {
        with(binding) {
            appbarLayout.toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            btnDone.setOnClickListener(this@BlogAddScreen)
        }
    }

    private fun observeUiStateFlows() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            authorViewModel.getAuthorName(authorId)
            authorViewModel.authorName.collect { name ->
                binding.textInputAuthor.setText(name)
            }
        }
    }

    private fun addBlog() {
        with(binding) {
            val title = textInputTitle.text.toString().trim()
            val description = textInputDescription.text.toString().trim()
            if (title.isNotEmpty() && description.isNotEmpty()) {
                blogViewModel.addNewBlog(Blog(authorId = authorId, title = title, description = description))
                Snackbar.make(root, getString(R.string.success_added_blog), Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(root, getString(R.string.err_blank_field), Snackbar.LENGTH_LONG)
                    .setTextColor(ResourcesCompat.getColor(resources, R.color.md_theme_dark_onErrorContainer, null))
                    .setBackgroundTint(ResourcesCompat.getColor(resources, R.color.md_theme_dark_errorContainer, null))
                    .show()
            }
        }
    }
}