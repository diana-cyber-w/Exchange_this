package com.example.exchangethis.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.RatingLayoutBinding
import com.example.exchangethis.presentation.viewModels.LibraryViewModel
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import org.koin.android.viewmodel.ext.android.sharedViewModel

class RatingFragment : Fragment(R.layout.rating_layout) {

    private val binding: RatingLayoutBinding by viewBinding(RatingLayoutBinding::bind)
    private val viewModel: LibraryViewModel by sharedViewModel()
    private val prefs by lazy { SharedPreferenceManagerImpl(requireContext()) }
    private var bookRating: Double = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.publisherRating.setOnRatingBarChangeListener { ratingBar, fl, b ->
            bookRating = fl.toDouble()
        }

        viewModel.getBookByTitle(prefs.getString(resources.getString(R.string.TITLE)))

        binding.ratingButton.setOnClickListener {
            bookRating = ((viewModel.bookByTitle.value?.get(0)?.rating ?: 0.0) + bookRating) / 2.0
            viewModel.updateRating(bookRating, prefs.getString(resources.getString(R.string.TITLE)))
            findNavController().navigate(R.id.toMenuFromRating)
        }
    }
}