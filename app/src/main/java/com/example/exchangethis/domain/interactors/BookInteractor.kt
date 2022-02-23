package com.example.exchangethis.domain.interactors

import com.example.exchangethis.domain.models.Book
import kotlinx.coroutines.flow.Flow

interface BookInteractor {

    fun getAllBooks(): Flow<List<Book>>

    fun getMyBooks(bookEmail: String): Flow<List<Book>>

    fun getFavouriteBooks(favourite: Boolean): Flow<List<Book>>

    suspend fun insertBook(book: Book)

    suspend fun deleteBook(bookEmail: String, bookName: String)

    suspend fun updateBook(favourite: Boolean, bookName: String)

    suspend fun updateBookRating(rating: Double, bookName: String)

    fun getBookByTitle(title: String): Flow<List<Book>>

    fun getBookByCategory(category: String): Flow<List<Book>>
}