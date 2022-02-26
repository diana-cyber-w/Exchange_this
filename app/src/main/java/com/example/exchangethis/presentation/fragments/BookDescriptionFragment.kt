package com.example.exchangethis.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.BookDescriptionLayoutBinding
import com.example.exchangethis.presentation.viewModels.LibraryViewModel
import com.example.exchangethis.presentation.viewModels.MyBookViewModel
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf

class BookDescriptionFragment : Fragment(R.layout.book_description_layout) {

    private val binding: BookDescriptionLayoutBinding by viewBinding(BookDescriptionLayoutBinding::bind)
    private val libraryViewModel: LibraryViewModel by sharedViewModel()
    private val prefs by lazy { SharedPreferenceManagerImpl(requireContext()) }
    private val bookViewModel: MyBookViewModel by sharedViewModel()
    private var bookName: String = ""
    private var bookYearAndAuthor: String = ""
    private var bookDescription: String = ""
    private var bookEmail: String = ""
    private var publisherName: String = ""
    private var publisherPhone: String = ""
    private var bookTotalRating: Double = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        libraryViewModel.getBookByTitle(prefs.getString(resources.getString(R.string.TITLE)))
        fillBookData()

        binding.ratingButton.setOnClickListener {
            findNavController().navigate(R.id.toRating)
        }

        binding.toMenu.setOnClickListener {
            findNavController().navigate(R.id.toMenuFromDescription)
        }

        binding.callPublisher.setOnClickListener {
            startCallIntent()
        }
    }

    private fun fillBookData() {
        libraryViewModel.bookByTitle.observe(viewLifecycleOwner) { book ->
            bookName = book[0].bookName
            bookDescription = book[0].description
            bookYearAndAuthor = "${book[0].year}, ${book[0].author}"
            bookEmail = book[0].bookEmail

            binding.bookName.text = bookName
            binding.bookDescription.text = bookDescription
            binding.bookYearAndAuthor.text = bookYearAndAuthor
        }

        publisherName = prefs.getString(resources.getString(R.string.PUBLISHER_NAME))
        publisherPhone = prefs.getString(resources.getString(R.string.PUBLISHER_PHONE))

        binding.publisherName.text = publisherName
        binding.publisherPhone.text = publisherPhone

        bookViewModel.getMyBookRating(prefs.getString(resources.getString(R.string.EMAIL_KEY)))
        setAverageRating()

        bookViewModel.averageBookRating.observe(viewLifecycleOwner) { rating ->
            viewLifecycleOwner.lifecycleScope.launch {
                binding.publisherRating.rating = rating
            }
        }
    }

    private fun setAverageRating() {
        bookViewModel.myBookRating.observe(viewLifecycleOwner) { books ->
            viewLifecycleOwner.lifecycleScope.launch {
                books?.forEach { myBook ->
                    bookTotalRating += myBook.rating
                }
            }
            prefs.saveFloat(
                resources.getString(R.string.RATING_KEY),
                (bookTotalRating / prefs.getInt(resources.getString(R.string.COUNTER_KEY))).toFloat()
            )
            bookViewModel.countAverageBookRating(prefs.getFloat(resources.getString(R.string.RATING_KEY)))
        }
    }

    private fun startCallIntent() {
        val intentPhone = Intent(Intent.ACTION_DIAL).apply {
            data =
                Uri.parse("tel:" + prefs.getString(resources.getString(R.string.PUBLISHER_PHONE)))
        }
        startActivity(intentPhone)
    }
}