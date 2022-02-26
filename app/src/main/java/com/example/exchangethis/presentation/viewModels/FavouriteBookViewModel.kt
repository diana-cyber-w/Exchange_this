package com.example.exchangethis.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangethis.domain.interactors.BookInteractor
import com.example.exchangethis.domain.models.Book
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavouriteBookViewModel(
    private val bookInteractor: BookInteractor
) : ViewModel() {

    val books: LiveData<List<Book>> get() = _books
    private val _books = MutableLiveData<List<Book>>()

    init {
        getFavouriteBooks(true)
    }

    private fun getFavouriteBooks(favourite: Boolean) {
        viewModelScope.launch {
            bookInteractor.getFavouriteBooks(favourite).collect { books ->
                _books.value = books
            }
        }
    }

    fun updateBook(favourite: Boolean, bookName: String) {
        viewModelScope.launch {
            bookInteractor.updateBook(favourite, bookName)
        }
    }
}