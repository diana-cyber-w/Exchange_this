package com.example.exchangethis.data

import com.example.exchangethis.data.storage.book.BookEntity
import com.example.exchangethis.data.storage.user.UserEntity
import com.example.exchangethis.domain.models.Book
import com.example.exchangethis.domain.models.User

fun UserEntity.toUser() =
    User(
        fullName = fullName,
        email = email,
        phone = phone,
        password = password
    )

fun User.toUserEntity() =
    UserEntity(
        fullName = fullName,
        email = email,
        phone = phone,
        password = password
    )

fun BookEntity.toBook() =
    Book(
        bookEmail = bookEmail,
        bookName = bookName,
        author = author,
        year = year,
        description = description,
        rating = rating,
        favourite = isFavourite,
        bookCategory = bookCategory,
        bookImage = bookImage
    )

fun Book.toBookEntity() =
    BookEntity(
        bookEmail = bookEmail,
        bookName = bookName,
        author = author,
        year = year,
        description = description,
        rating = rating,
        isFavourite = favourite,
        bookCategory = bookCategory,
        bookImage = bookImage
    )