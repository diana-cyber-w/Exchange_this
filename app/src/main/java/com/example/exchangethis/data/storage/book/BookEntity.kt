package com.example.exchangethis.data.storage.book

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
class BookEntity(
    @ColumnInfo(name = "bookEmail")
    val bookEmail: String,

    @ColumnInfo(name = "bookName")
    val bookName: String,

    @ColumnInfo(name = "author")
    val author: String,

    @ColumnInfo(name = "year")
    val year: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "isFavourite")
    val isFavourite: Boolean,

    @ColumnInfo(name = "bookCategory")
    val bookCategory: String,

    @ColumnInfo(name = "bookImage")
    val bookImage: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}