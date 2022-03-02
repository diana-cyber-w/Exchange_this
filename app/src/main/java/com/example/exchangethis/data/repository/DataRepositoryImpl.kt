package com.example.exchangethis.data.repository

import com.example.exchangethis.data.storage.book.BookDao
import com.example.exchangethis.data.storage.book.BookEntity
import com.example.exchangethis.data.storage.user.UserDao
import com.example.exchangethis.data.storage.user.UserEntity
import com.example.exchangethis.data.toBook
import com.example.exchangethis.data.toBookEntity
import com.example.exchangethis.data.toUserEntity
import com.example.exchangethis.domain.models.Book
import com.example.exchangethis.domain.models.User
import com.example.exchangethis.domain.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class DataRepositoryImpl(
    private val userDao: UserDao,
    private val bookDao: BookDao
) : DataRepository {

    override fun getUsers(): Flow<List<UserEntity>> {
        return flow {
            emit(userDao.getUsers())
        }
    }

    override fun getUserByEmailAndPassword(
        email: String,
        password: String
    ): Flow<List<UserEntity>> {
        return flow {
            emit(userDao.getUserByEmailAmdPassword(email, password))
        }
    }

    override fun getUserByEmail(email: String): Flow<List<UserEntity>> {
        return flow {
            emit(userDao.getUserByEmail(email))
        }
    }

    override suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insertUser(user.toUserEntity())
        }
    }

    override suspend fun updateUser(
        fullName: String,
        phone: String,
        password: String,
        email: String
    ) {
        withContext(Dispatchers.IO) {
            userDao.updateUser(fullName, phone, password, email)
        }
    }

    override fun getAllBooks(): Flow<List<BookEntity>> = flow {
        while (true) {
            emit(bookDao.getAllBooks())
            delay(DELAY_TIME)
        }
    }

    override fun getMyBooks(bookEmail: String): Flow<List<BookEntity>> = flow {
        while (true) {
            emit(bookDao.getMyBooks(bookEmail))
            delay(DELAY_TIME)
        }
    }

    override suspend fun getMyBooksRating(bookEmail: String): List<Book> {
        return withContext(Dispatchers.IO) {
            bookDao.getMyBooks(bookEmail).map { bookEntity ->
                bookEntity.toBook()
            }
        }
    }

    override suspend fun updateBook(favourite: Boolean, fullName: String) {
        withContext(Dispatchers.IO) {
            bookDao.updateBook(favourite, fullName)
        }
    }

    override suspend fun updateBookRating(rating: Double, fullName: String) {
        withContext(Dispatchers.IO) {
            bookDao.updateBookRating(rating, fullName)
        }
    }

    override suspend fun insertBook(book: Book) {
        withContext(Dispatchers.IO) {
            bookDao.insertBook(book.toBookEntity())
        }
    }

    override suspend fun deleteBook(bookEmail: String, bookName: String) {
        withContext(Dispatchers.IO) {
            bookDao.deleteBook(bookEmail, bookName)
        }
    }

    override fun getFavouriteBooks(favourite: Boolean): Flow<List<BookEntity>> = flow {
        while (true) {
            emit(bookDao.getFavouriteBooks(favourite))
            delay(DELAY_TIME)
        }
    }


    override fun getBookByTitle(title: String): Flow<List<BookEntity>> = flow {
        emit(bookDao.getBookByTitle(title))
    }

    override fun getBookByCategory(category: String): Flow<List<BookEntity>> = flow {
        emit(bookDao.getBookByCategory(category))
    }

    companion object {
        private const val DELAY_TIME = 1000L
    }
}