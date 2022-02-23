package com.example.exchangethis.data.storage.book

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getAllBooks(): List<BookEntity>

    @Query("SELECT * FROM books WHERE bookEmail =:bookEmail")
    fun getMyBooks(bookEmail: String): List<BookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book: BookEntity)

    @Query("DELETE FROM books WHERE bookEmail LIKE :bookEmail AND bookName LIKE :bookName")
    fun deleteBook(bookEmail: String, bookName: String)

    @Query("SELECT * FROM books WHERE isFavourite LIKE :favourite")
    fun getFavouriteBooks(favourite: Boolean): List<BookEntity>

    @Query("UPDATE books SET isFavourite =:favourite WHERE bookName LIKE :bookName")
    fun updateBook(favourite: Boolean, bookName: String)

    @Query("UPDATE books SET rating =:rating WHERE bookName LIKE :bookName")
    fun updateBookRating(rating: Double, bookName: String)

    @Query("SELECT * FROM books WHERE bookName LIKE :title")
    fun getBookByTitle(title: String): List<BookEntity>

    @Query("SELECT * FROM books WHERE bookCategory LIKE :category")
    fun getBookByCategory(category: String): List<BookEntity>
}