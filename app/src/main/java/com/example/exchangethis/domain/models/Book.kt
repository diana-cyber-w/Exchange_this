package com.example.exchangethis.domain.models

data class Book(
    var bookEmail: String,
    var bookName: String,
    var author: String,
    var year: String,
    var description: String,
    var rating: Double,
    var favourite: Boolean,
    var bookCategory: String,
    var bookImage: String
)