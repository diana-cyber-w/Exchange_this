package com.example.exchangethis.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.MyBookLayoutBinding
import com.example.exchangethis.domain.models.Book
import com.example.exchangethis.presentation.recycler.MyBookAdapter
import com.example.exchangethis.presentation.recycler.OnBookClickListener
import com.example.exchangethis.presentation.viewModels.MyBookViewModel
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MyBookFragment : Fragment(R.layout.my_book_layout) {

    private val binding: MyBookLayoutBinding by viewBinding(MyBookLayoutBinding::bind)
    private val adapter by lazy { MyBookAdapter(myBookListener) }
    private val prefs by lazy { SharedPreferenceManagerImpl(requireContext()) }
    private val viewModel: MyBookViewModel by sharedViewModel()

    private val myBookListener: OnBookClickListener = object : OnBookClickListener {
        override fun onIconClickListener(position: Int) {
            viewModel.deleteBook(
                prefs.getString(resources.getString(R.string.EMAIL_KEY)),
                viewModel.myBook.value?.get(position)?.bookName.orEmpty()
            )
            prefs.saveInt(
                resources.getString(R.string.COUNTER_KEY),
                prefs.getInt(resources.getString(R.string.COUNTER_KEY)) - 1
            )
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
            binding.recyclerMyBook.layoutManager = this
        }
    }

    private fun initRecycler() {
        binding.recyclerMyBook.adapter = adapter
    }

    private fun initObserves() {
        viewModel.myBook.observe(viewLifecycleOwner) { books ->
            adapter.submitList(books)
            if (books.isEmpty()) {
                binding.myBookNull.text = resources.getString(R.string.MY_BOOK_NULL)
            } else {
                binding.myBookNull.text = null
            }
        }
    }
}