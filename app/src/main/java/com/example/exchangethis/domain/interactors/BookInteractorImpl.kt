package com.example.exchangethis.domain.interactors

import com.example.exchangethis.data.repository.DataRepositoryImpl
import com.example.exchangethis.data.toBook
import com.example.exchangethis.domain.models.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class BookInteractorImpl(
    private val dataRepositoryImpl: DataRepositoryImpl
) : BookInteractor {

    override fun getAllBooks(): Flow<List<Book>> =
        dataRepositoryImpl.getAllBooks().map { bookEntityList ->
            bookEntityList.map { bookEntity ->
                bookEntity.toBook()
            }
        }
            .flowOn(Dispatchers.IO)

    override fun getMyBooks(bookEmail: String): Flow<List<Book>> =
        dataRepositoryImpl.getMyBooks(bookEmail).map { bookEntityList ->
            bookEntityList.map { bookEntity ->
                bookEntity.toBook()
            }
        }
            .flowOn(Dispatchers.IO)

    override suspend fun getMyBooksRating(bookEmail: String): List<Book> {
        return withContext(Dispatchers.IO) {
            dataRepositoryImpl.getMyBooksRating(bookEmail)
        }
    }

    override fun getFavouriteBooks(favourite: Boolean): Flow<List<Book>> =
        dataRepositoryImpl.getFavouriteBooks(favourite).map { bookEntityList ->
            bookEntityList.map { bookEntity ->
                bookEntity.toBook()
            }
        }
            .flowOn(Dispatchers.IO)

    override suspend fun updateBook(favourite: Boolean, bookName: String) {
        withContext(Dispatchers.IO) {
            dataRepositoryImpl.updateBook(favourite, bookName)
        }
    }

    override suspend fun updateBookRating(rating: Double, bookName: String) {
        withContext(Dispatchers.IO) {
            dataRepositoryImpl.updateBookRating(rating, bookName)
        }
    }

    override suspend fun insertBook(book: Book) {
        withContext(Dispatchers.IO) {
            dataRepositoryImpl.insertBook(book)
        }
    }

    override suspend fun deleteBook(bookEmail: String, bookName: String) {
        withContext(Dispatchers.IO) {
            dataRepositoryImpl.deleteBook(bookEmail, bookName)
        }
    }

    override fun getBookByTitle(title: String): Flow<List<Book>> =
        dataRepositoryImpl.getBookByTitle(title).map { bookEntityList ->
            bookEntityList.map { bookEntity ->
                bookEntity.toBook()
            }
        }
            .flowOn(Dispatchers.IO)

    override fun getBookByCategory(category: String): Flow<List<Book>> =
        dataRepositoryImpl.getBookByCategory(category).map { bookEntityList ->
            bookEntityList.map { bookEntity ->
                bookEntity.toBook()
            }
        }
            .flowOn(Dispatchers.IO)
}