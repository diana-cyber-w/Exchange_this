package com.example.exchangethis.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.FavouriteBooksLayoutBinding
import com.example.exchangethis.domain.models.Book
import com.example.exchangethis.presentation.recycler.LibraryAdapter
import com.example.exchangethis.presentation.recycler.OnBookClickListener
import com.example.exchangethis.presentation.viewModels.FavouriteBookViewModel
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FavouriteBooksFragment : Fragment(R.layout.favourite_books_layout) {

    private val binding: FavouriteBooksLayoutBinding by viewBinding(FavouriteBooksLayoutBinding::bind)
    private val prefs by lazy { SharedPreferenceManagerImpl(requireContext()) }
    private val adapter by lazy { LibraryAdapter(bookListener) }
    private val viewModel: FavouriteBookViewModel by sharedViewModel()

    private val bookListener: OnBookClickListener = object : OnBookClickListener {
        override fun onIconClickListener(position: Int) {
            val key = viewModel.books.value?.get(position)?.bookName
            viewModel.updateBook(
                !prefs.getBoolean(key.orEmpty()),
                viewModel.books.value?.get(position)?.bookName.orEmpty()
            )
            prefs.saveBoolean(key.orEmpty(), !prefs.getBoolean(key.orEmpty()))
        }

        override fun onItemClickListener(book: Book) {
            prefs.saveString(resources.getString(R.string.TITLE), book.bookName)
            findNavController().navigate(R.id.toDescription)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObserves()
        gridLayout()
    }

    private fun gridLayout() {
        GridLayoutManager(
            requireContext(),
            2,
            RecyclerView.VERTICAL,
            false
        ).apply {
            binding.recyclerFavourite.layoutManager = this
        }
    }

    private fun initRecycler() {
        binding.recyclerFavourite.adapter = adapter
    }

    private fun initObserves() {
        viewModel.books.observe(viewLifecycleOwner) { books ->
            adapter.submitList(books)
            if (books.isEmpty()) {
                binding.favouriteNull.text = resources.getString(R.string.FAVOURITE_NULL)
            } else {
                binding.favouriteNull.text = null
            }
        }
    }
}