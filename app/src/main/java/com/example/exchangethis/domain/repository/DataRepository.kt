package com.example.exchangethis.domain.repository

import com.example.exchangethis.domain.models.Book
import com.example.exchangethis.domain.models.User
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getUsers(): Flow<List<User>>

    fun getUserByEmailAndPassword(email: String, password: String): Flow<List<User>>

    fun getUserByEmail(email: String): Flow<List<User>>

    suspend fun insertUser(user: User)

    suspend fun updateUser(
        fullName: String,
        phone: String,
        password: String,
        email: String
    )

    fun getAllBooks(): Flow<List<Book>>

    fun getMyBooks(bookEmail: String): Flow<List<Book>>

    suspend fun insertBook(book: Book)

    suspend fun deleteBook(bookEmail: String, bookName: String)

    fun getFavouriteBooks(favourite: Boolean): Flow<List<Book>>

    suspend fun updateBook(favourite: Boolean, fullName: String)

    suspend fun updateBookRating(rating: Double, fullName: String)

    fun getBookByTitle(title: String): Flow<List<Book>>

    fun getBookByCategory(category: String): Flow<List<Book>>
}