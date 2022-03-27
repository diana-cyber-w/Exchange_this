package com.example.exchangethis.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangethis.domain.interactors.BookInteractor
import com.example.exchangethis.domain.models.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LibraryViewModel(
    private val bookInteractor: BookInteractor
) : ViewModel() {

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

    fun getBookByCategory(category: String) {
        viewModelScope.launch {
            bookInteractor.getBookByCategory(category).collect { books ->
                _bookByCategory.value = books
            }
        }
    }

    fun getImage(title: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.Default) {
                val part = async {
                    return@async bookInteractor.getBookImage(title)
                }
                return@withContext part.await()
            }
            _image.value = result
        }
    }
}