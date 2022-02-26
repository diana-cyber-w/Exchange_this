package com.example.exchangethis.domain.repository

import com.example.exchangethis.data.storage.book.BookEntity
import com.example.exchangethis.data.storage.user.UserEntity
import com.example.exchangethis.domain.models.Book
import com.example.exchangethis.domain.models.User
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getUsers(): Flow<List<UserEntity>>

    fun getUserByEmailAndPassword(email: String, password: String): Flow<List<UserEntity>>

    fun getUserByEmail(email: String): Flow<List<UserEntity>>

    suspend fun insertUser(user: User)

    suspend fun updateUser(
        fullName: String,
        phone: String,
        password: String,
        email: String
    )

    fun getAllBooks(): Flow<List<BookEntity>>

    fun getMyBooks(bookEmail: String): Flow<List<BookEntity>>

    suspend fun getMyBooksRating(bookEmail: String): List<Book>

    suspend fun insertBook(book: Book)

    suspend fun deleteBook(bookEmail: String, bookName: String)

    fun getFavouriteBooks(favourite: Boolean): Flow<List<BookEntity>>

    suspend fun updateBook(favourite: Boolean, fullName: String)

    suspend fun updateBookRating(rating: Double, fullName: String)

    fun getBookByTitle(title: String): Flow<List<BookEntity>>

    fun getBookByCategory(category: String): Flow<List<BookEntity>>
}