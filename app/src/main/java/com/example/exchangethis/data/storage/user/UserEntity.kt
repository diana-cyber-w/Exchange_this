package com.example.exchangethis.data.storage.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class UserEntity(

    @ColumnInfo(name = "fullName")
    val fullName: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "phone")
    val phone: String,

    @ColumnInfo(name = "password")
    val password: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}