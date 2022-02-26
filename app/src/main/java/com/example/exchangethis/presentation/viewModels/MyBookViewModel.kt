package com.example.exchangethis.presentation.viewModels

import android.content.Context
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

class MyBookViewModel(
    private val bookInteractor: BookInteractor,
    context: Context
) : ViewModel() {

    private val prefs by lazy { SharedPreferenceManagerImpl(context) }

    val myBook: LiveData<List<Book>> get() = _myBook
    private val _myBook = MutableLiveData<List<Book>>()

    val averageBookRating: LiveData<Float> get() = _averageBookRating
    private val _averageBookRating = MutableLiveData<Float>()

    val myBookRating: LiveData<List<Book>> get() = _myBookRating
    private val _myBookRating = MutableLiveData<List<Book>>()

    init {
        getMyBooks(prefs.getString(context.getString(R.string.EMAIL_KEY)))
    }

    private fun getMyBooks(email: String) {
        viewModelScope.launch {
            bookInteractor.getMyBooks(email).collect { books ->
                _myBook.value = books
            }
        }
    }

    fun deleteBook(email: String, bookName: String) {
        viewModelScope.launch {
            bookInteractor.deleteBook(email, bookName)
        }
    }

    fun countAverageBookRating(rating: Float) {
        _averageBookRating.value = rating
    }

    fun getMyBookRating(email: String) {
        viewModelScope.launch {
            _myBookRating.value = bookInteractor.getMyBooksRating(email)
        }
    }
}