package com.devbytes.app.blogapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import com.devbytes.app.blogapp.R
import com.devbytes.app.blogapp.databinding.AuthorAddModalSheetBinding
import com.devbytes.app.blogapp.ui.viewmodel.AuthorViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorAddModalSheet : BottomSheetDialogFragment(), View.OnClickListener {
    private var _binding: AuthorAddModalSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<AuthorViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AuthorAddModalSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeClickListeners()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_done -> {
                addAuthor()
            }

            R.id.btn_cancel -> {
                dismiss()
            }
        }
    }

    private fun initializeClickListeners() {
        with(binding) {
            btnDone.setOnClickListener(this@AuthorAddModalSheet)
            btnCancel.setOnClickListener(this@AuthorAddModalSheet)
        }
    }

    private fun addAuthor() {
        with(binding) {
            val authorName = textInputName.text.toString()

            if(authorName.isEmpty()) {
                showError(getString(R.string.err_blank_field))
                return
            }

            viewModel.addNewAuthor(authorName.trim())
            dismiss()
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.md_theme_dark_onErrorContainer,
                    null
                )
            )
            .setBackgroundTint(
                ResourcesCompat.getColor(
                    resources,
                    R.color.md_theme_dark_errorContainer,
                    null
                )
            )
            .show()
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}