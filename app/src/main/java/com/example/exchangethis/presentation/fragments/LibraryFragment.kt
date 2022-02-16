package com.example.exchangethis.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.LibraryLayoutBinding

class LibraryFragment : Fragment(R.layout.library_layout) {

    private val binding: LibraryLayoutBinding by viewBinding(LibraryLayoutBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.toRegistration)
        }
    }
}