package com.example.exchangethis.presentation.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.exchangethis.R
import com.example.exchangethis.databinding.AddBookLayoutBinding
import com.example.exchangethis.domain.models.Book
import com.example.exchangethis.presentation.viewModels.LibraryViewModel
import com.example.exchangethis.presentation.viewModels.UserViewModel
import com.example.exchangethis.utils.MyUtils
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AddBookFragment : Fragment(R.layout.add_book_layout) {

    private val binding: AddBookLayoutBinding by viewBinding(AddBookLayoutBinding::bind)
    private val viewModel: LibraryViewModel by viewModel()
    private val userViewModel: UserViewModel by sharedViewModel()
    private val prefs by lazy { SharedPreferenceManagerImpl(requireContext()) }
    private var bookName: String = resources.getString(R.string.STRING_DEFAULT_VALUE)
    private var bookAuthor: String = resources.getString(R.string.STRING_DEFAULT_VALUE)
    private var bookYear: String = resources.getString(R.string.STRING_DEFAULT_VALUE)
    private var bookEmail: String = resources.getString(R.string.STRING_DEFAULT_VALUE)
    private var bookDescription: String = resources.getString(R.string.STRING_DEFAULT_VALUE)
    private var rating: Double = 0.0
    private val favourite: Boolean = false
    private var bookCategory: String = resources.getString(R.string.STRING_DEFAULT_VALUE)
    private val newBook by lazy {
        Book(
            bookEmail,
            bookName,
            bookAuthor,
            bookYear,
            bookDescription,
            rating,
            favourite,
            bookCategory
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addBookButton.setOnClickListener {
            validateBook()
        }

        setButtonView()
    }

    private fun validateBook() {

        bookEmail = prefs.getString(resources.getString(R.string.EMAIL_KEY))
        bookName = binding.myBookName.text.toString().trim()
        bookAuthor = binding.myBookAuthor.text.toString().trim()
        bookYear = binding.myBookYear.text.toString().trim()
        bookDescription = binding.myBookDescription.text.toString().trim()

        userViewModel.getUserByEmail(bookEmail)

        userViewModel.userByEmail.observe(viewLifecycleOwner) { publisher ->
            viewLifecycleOwner.lifecycleScope.launch {
                prefs.saveString(
                    resources.getString(R.string.PUBLISHER_NAME),
                    publisher[0].fullName
                )
                prefs.saveString(resources.getString(R.string.PUBLISHER_PHONE), publisher[0].phone)
            }
        }

        if (TextUtils.isEmpty(bookName)) {
            binding.myBookName.error = resources.getString(R.string.TITLE_EMPTY_ERROR)
        } else if (TextUtils.isEmpty(bookAuthor)) {
            binding.myBookAuthor.error = resources.getString(R.string.AUTHOR_EMPTY_ERROR)
        } else if (TextUtils.isEmpty(bookYear) || bookYear.length != 4) {
            binding.myBookYear.error = resources.getString(R.string.YEAR_EMPTY_ERROR)
        } else if (TextUtils.isEmpty(bookDescription)) {
            binding.myBookDescription.error = resources.getString(R.string.DESCRIPTION_EMPTY_ERROR)
        } else {
            prefs.saveInt(
                resources.getString(R.string.COUNTER_KEY),
                prefs.getInt(resources.getString(R.string.COUNTER_KEY)) + 1
            )
            viewModel.insertBook(newBook)
            userViewModel.setBookCounter(prefs.getInt(resources.getString(R.string.COUNTER_KEY)))
            findNavController().navigate(R.id.toMyBook)
        }
    }

    private fun setButtonView() {
        binding.fiction.setOnClickListener {
            prefs.saveBoolean(
                resources.getString(R.string.fiction_text),
                !prefs.getBoolean(resources.getString(R.string.fiction_text))
            )
            if (prefs.getBoolean(resources.getString(R.string.fiction_text))) {
                binding.fiction.setBackgroundResource(R.drawable.category_active)
                MyUtils.disableButton(
                    binding.nonFiction,
                    binding.cook,
                    binding.drama,
                    binding.fantasy,
                    binding.thriller,
                    binding.another
                )
                bookCategory = binding.fiction.text.toString()
            } else {
                binding.fiction.setBackgroundResource(R.drawable.category_inactive)
                MyUtils.enableButton(
                    binding.nonFiction,
                    binding.cook,
                    binding.drama,
                    binding.fantasy,
                    binding.thriller,
                    binding.another
                )
                bookCategory = resources.getString(R.string.STRING_DEFAULT_VALUE)
            }
        }
        binding.nonFiction.setOnClickListener {
            prefs.saveBoolean(
                resources.getString(R.string.non_fiction_text),
                !prefs.getBoolean(resources.getString(R.string.non_fiction_text))
            )
            if (prefs.getBoolean(resources.getString(R.string.non_fiction_text))) {
                binding.nonFiction.setBackgroundResource(R.drawable.category_active)
                MyUtils.disableButton(
                    binding.fiction,
                    binding.cook,
                    binding.drama,
                    binding.fantasy,
                    binding.thriller,
                    binding.another
                )
                bookCategory = binding.nonFiction.text.toString()
            } else {
                binding.nonFiction.setBackgroundResource(R.drawable.category_inactive)
                MyUtils.enableButton(
                    binding.fiction,
                    binding.cook,
                    binding.drama,
                    binding.fantasy,
                    binding.thriller,
                    binding.another
                )
                bookCategory = resources.getString(R.string.STRING_DEFAULT_VALUE)
            }
        }
        binding.cook.setOnClickListener {
            prefs.saveBoolean(
                resources.getString(R.string.cook_text),
                !prefs.getBoolean(resources.getString(R.string.cook_text))
            )
            if (prefs.getBoolean(resources.getString(R.string.cook_text))) {
                binding.cook.setBackgroundResource(R.drawable.category_active)
                MyUtils.disableButton(
                    binding.fiction,
                    binding.nonFiction,
                    binding.drama,
                    binding.fantasy,
                    binding.thriller,
                    binding.another
                )
                bookCategory = binding.cook.text.toString()
            } else {
                binding.cook.setBackgroundResource(R.drawable.category_inactive)
                MyUtils.enableButton(
                    binding.nonFiction,
                    binding.fiction,
                    binding.drama,
                    binding.fantasy,
                    binding.thriller,
                    binding.another
                )
                bookCategory = resources.getString(R.string.STRING_DEFAULT_VALUE)
            }
        }
        binding.drama.setOnClickListener {
            prefs.saveBoolean(
                resources.getString(R.string.drama_text),
                !prefs.getBoolean(resources.getString(R.string.drama_text))
            )
            if (prefs.getBoolean(resources.getString(R.string.drama_text))) {
                binding.drama.setBackgroundResource(R.drawable.category_active)
                MyUtils.disableButton(
                    binding.fiction,
                    binding.cook,
                    binding.nonFiction,
                    binding.fantasy,
                    binding.thriller,
                    binding.another
                )
                bookCategory = binding.drama.text.toString()
            } else {
                binding.drama.setBackgroundResource(R.drawable.category_inactive)
                MyUtils.enableButton(
                    binding.nonFiction,
                    binding.fiction,
                    binding.cook,
                    binding.fantasy,
                    binding.thriller,
                    binding.another
                )
                bookCategory = resources.getString(R.string.STRING_DEFAULT_VALUE)
            }
        }
        binding.fantasy.setOnClickListener {
            prefs.saveBoolean(
                resources.getString(R.string.fantasy_text),
                !prefs.getBoolean(resources.getString(R.string.fantasy_text))
            )
            if (prefs.getBoolean(resources.getString(R.string.fantasy_text))) {
                binding.fantasy.setBackgroundResource(R.drawable.category_active)
                MyUtils.disableButton(
                    binding.fiction,
                    binding.cook,
                    binding.nonFiction,
                    binding.drama,
                    binding.thriller,
                    binding.another
                )
                bookCategory = binding.fantasy.text.toString()
            } else {
                binding.fantasy.setBackgroundResource(R.drawable.category_inactive)
                MyUtils.enableButton(
                    binding.nonFiction,
                    binding.fiction,
                    binding.drama,
                    binding.cook,
                    binding.thriller,
                    binding.another
                )
                bookCategory = resources.getString(R.string.STRING_DEFAULT_VALUE)
            }
        }
        binding.thriller.setOnClickListener {
            prefs.saveBoolean(
                resources.getString(R.string.thriller_text),
                !prefs.getBoolean(resources.getString(R.string.thriller_text))
            )
            if (prefs.getBoolean(resources.getString(R.string.thriller_text))) {
                binding.thriller.setBackgroundResource(R.drawable.category_active)
                MyUtils.disableButton(
                    binding.fiction,
                    binding.cook,
                    binding.nonFiction,
                    binding.fantasy,
                    binding.drama,
                    binding.another
                )
                bookCategory = binding.thriller.text.toString()
            } else {
                binding.thriller.setBackgroundResource(R.drawable.category_inactive)
                MyUtils.enableButton(
                    binding.nonFiction,
                    binding.fiction,
                    binding.drama,
                    binding.cook,
                    binding.fantasy,
                    binding.another
                )
                bookCategory = resources.getString(R.string.STRING_DEFAULT_VALUE)
            }
        }
        binding.another.setOnClickListener {
            prefs.saveBoolean(
                resources.getString(R.string.another_text),
                !prefs.getBoolean(resources.getString(R.string.another_text))
            )
            if (prefs.getBoolean(resources.getString(R.string.another_text))) {
                binding.another.setBackgroundResource(R.drawable.category_active)
                MyUtils.disableButton(
                    binding.fiction,
                    binding.cook,
                    binding.nonFiction,
                    binding.fantasy,
                    binding.drama,
                    binding.thriller
                )
                bookCategory = binding.another.text.toString()
            } else {
                binding.another.setBackgroundResource(R.drawable.category_inactive)
                MyUtils.enableButton(
                    binding.nonFiction,
                    binding.fiction,
                    binding.drama,
                    binding.cook,
                    binding.thriller,
                    binding.fantasy
                )
                bookCategory = resources.getString(R.string.STRING_DEFAULT_VALUE)
            }
        }
    }
}