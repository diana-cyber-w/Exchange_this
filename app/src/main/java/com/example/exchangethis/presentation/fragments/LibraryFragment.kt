package com.example.exchangethis.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.LibraryLayoutBinding
import com.example.exchangethis.domain.models.Book
import com.example.exchangethis.presentation.models.Category
import com.example.exchangethis.presentation.recycler.CategoryAdapter
import com.example.exchangethis.presentation.recycler.LibraryAdapter
import com.example.exchangethis.presentation.recycler.OnBookClickListener
import com.example.exchangethis.presentation.recycler.OnCategoryClickListener
import com.example.exchangethis.presentation.viewModels.CategoryViewModel
import com.example.exchangethis.presentation.viewModels.LibraryViewModel
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LibraryFragment : Fragment(R.layout.library_layout) {

    private val binding: LibraryLayoutBinding by viewBinding(LibraryLayoutBinding::bind)
    private val viewModel: LibraryViewModel by sharedViewModel()
    private val categoryViewModel: CategoryViewModel by viewModel()
    private val adapter by lazy { LibraryAdapter(bookListener) }
    private val categoryAdapter by lazy { CategoryAdapter(categoryListener) }
    private val prefs by lazy { SharedPreferenceManagerImpl(requireContext()) }

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

    private val categoryListener: OnCategoryClickListener = object : OnCategoryClickListener {
        override fun onItemClickListener(category: Category) {
            prefs.saveString(resources.getString(R.string.CATEGORY_KEY), category.category)
            findNavController().navigate(R.id.toCategory)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObserves()
        categoryViewModel.getCategory()
        binding.addBook.setOnClickListener {
            findNavController().navigate(R.id.toAddBook)
        }

        gridLayout()
    }

    private fun gridLayout() {
        GridLayoutManager(
            requireContext(),
            2,
            RecyclerView.VERTICAL,
            false
        ).apply {
            binding.recyclerBook.layoutManager = this
        }
    }

    private fun initRecycler() {
        binding.recyclerBook.adapter = adapter
        binding.recyclerCategory.adapter = categoryAdapter
    }

    private fun initObserves() {
        viewModel.books.observe(viewLifecycleOwner) { books ->
            adapter.submitList(books)
            if (books.isEmpty()) {
                binding.bookNull.text = resources.getString(R.string.BOOK_NULL)
            } else {
                binding.bookNull.text = null
            }
        }
        categoryViewModel.category.observe(viewLifecycleOwner) { category ->
            categoryAdapter.submitList(category)
        }
    }
}