package com.example.exchangethis.data.repository

import com.example.exchangethis.data.storage.book.BookDao
import com.example.exchangethis.data.storage.user.UserDao
import com.example.exchangethis.data.toBook
import com.example.exchangethis.data.toBookEntity
import com.example.exchangethis.data.toUser
import com.example.exchangethis.data.toUserEntity
import com.example.exchangethis.domain.models.Book
import com.example.exchangethis.domain.models.User
import com.example.exchangethis.domain.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class DataRepositoryImpl(
    private val userDao: UserDao,
    private val bookDao: BookDao
) : DataRepository {

    override fun getUsers(): Flow<List<User>> {
        return flow {
            val users = userDao.getUsers().map { userEntity ->
                userEntity.toUser()
            }
            emit(users)
        }
    }

    override fun getUserByEmailAndPassword(email: String, password: String): Flow<List<User>> {
        return flow {
            val user = userDao.getUserByEmailAmdPassword(email, password).map { userEntity ->
                userEntity.toUser()
            }
            emit(user)
        }
    }

    override fun getUserByEmail(email: String): Flow<List<User>> {
        return flow {
            val user = userDao.getUserByEmail(email).map { userEntity ->
                userEntity.toUser()
            }
            emit(user)
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

    override fun getAllBooks(): Flow<List<Book>> {
        return flow {
            val book = bookDao.getAllBooks().map { bookEntity ->
                bookEntity.toBook()
            }
            emit(book)
        }
    }

    override fun getMyBooks(bookEmail: String): Flow<List<Book>> {
        return flow {
            val book = bookDao.getMyBooks(bookEmail).map { bookEntity ->
                bookEntity.toBook()
            }
            emit(book)
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

    override fun getFavouriteBooks(favourite: Boolean): Flow<List<Book>> {
        return flow {
            val books = bookDao.getFavouriteBooks(favourite).map { bookEntity ->
                bookEntity.toBook()
            }
            emit(books)
        }
    }

    override fun getBookByTitle(title: String): Flow<List<Book>> {
        return flow {
            val books = bookDao.getBookByTitle(title).map { bookEntity ->
                bookEntity.toBook()
            }
            emit(books)
        }
    }

    override fun getBookByCategory(category: String): Flow<List<Book>> {
        return flow {
            val books = bookDao.getBookByCategory(category).map { bookEntity ->
                bookEntity.toBook()
            }
            emit(books)
        }
    }
}