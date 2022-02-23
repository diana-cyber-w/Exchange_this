package com.example.exchangethis.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.BookByCategoryLayoutBinding
import com.example.exchangethis.domain.models.Book
import com.example.exchangethis.presentation.recycler.LibraryAdapter
import com.example.exchangethis.presentation.recycler.OnBookClickListener
import com.example.exchangethis.presentation.viewModels.LibraryViewModel
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import org.koin.android.viewmodel.ext.android.sharedViewModel

class BookByCategoryFragment : Fragment(R.layout.book_by_category_layout) {

    private val binding: BookByCategoryLayoutBinding by viewBinding(BookByCategoryLayoutBinding::bind)
    private val prefs by lazy { SharedPreferenceManagerImpl(requireContext()) }
    private val viewModel: LibraryViewModel by sharedViewModel()
    private val adapter by lazy { LibraryAdapter(bookListener) }

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
        viewModel.getBookByCategory(prefs.getString(resources.getString(R.string.CATEGORY_KEY)))

        binding.toMenu.setOnClickListener {
            findNavController().navigate(R.id.toLibrary)
        }
    }

    private fun gridLayout() {
        GridLayoutManager(
            requireContext(),
            2,
            RecyclerView.VERTICAL,
            false
        ).apply {
            binding.recyclerCategoryBook.layoutManager = this
        }
    }

    private fun initRecycler() {
        binding.recyclerCategoryBook.adapter = adapter
    }

    private fun initObserves() {
        viewModel.bookByCategory.observe(viewLifecycleOwner) { books ->
            adapter.submitList(books)
            if (books.isEmpty()) {
                binding.categoryNull.text = resources.getString(R.string.CATEGORY_NULL)
            } else {
                binding.categoryNull.text = null
            }
        }
    }
}