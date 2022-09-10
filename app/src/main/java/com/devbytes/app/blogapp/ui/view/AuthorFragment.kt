package com.devbytes.app.blogapp.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devbytes.app.blogapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.author_fragment, container, false)
    }
}