package com.example.exchangethis.presentation.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangethis.R
import com.example.exchangethis.domain.interactors.BookInteractor
import com.example.exchangethis.domain.models.Book
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LibraryViewModel(
    private val bookInteractor: BookInteractor,
    context: Context
) : ViewModel() {

    private val prefs by lazy { SharedPreferenceManagerImpl(context) }

    val books: LiveData<List<Book>> get() = _books
    private val _books = MutableLiveData<List<Book>>()

    val bookByTitle: LiveData<List<Book>> get() = _bookByTitle
    private val _bookByTitle = MutableLiveData<List<Book>>()

    val bookByCategory: LiveData<List<Book>> get() = _bookByCategory
    private val _bookByCategory = MutableLiveData<List<Book>>()

    val image: LiveData<String> get() = _image
    private val _image = MutableLiveData<String>()

    init {
        getAllBooks()
        getBookByCategory(prefs.getString(context.getString(R.string.CATEGORY_KEY)))
    }

    private fun getAllBooks() {
        viewModelScope.launch {
            bookInteractor.getAllBooks().collect { books ->
                _books.value = books
            }
        }
    }

    fun insertBook(book: Book) {
        viewModelScope.launch {
            bookInteractor.insertBook(book)
        }
    }

    fun updateBook(favourite: Boolean, bookName: String) {
        viewModelScope.launch {
            bookInteractor.updateBook(favourite, bookName)
        }
    }

    fun getBookByTitle(title: String) {
        viewModelScope.launch {
            bookInteractor.getBookByTitle(title).collect { books ->
                _bookByTitle.value = books
            }
        }
    }

    fun updateRating(rating: Double, bookName: String) {
        viewModelScope.launch {
            bookInteractor.updateBookRating(rating, bookName)
        }
    }

    private fun getBookByCategory(category: String) {
        viewModelScope.launch {
            bookInteractor.getBookByCategory(category).collect { books ->
                _bookByCategory.value = books
            }
        }
    }

    fun getImage(title: String) {
        Log.e("IMAGE", _image.value.toString())
        viewModelScope.launch {
            _image.value = bookInteractor.getBookImage(title)
        }
    }
}