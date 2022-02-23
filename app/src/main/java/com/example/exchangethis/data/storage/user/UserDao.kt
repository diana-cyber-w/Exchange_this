package com.example.exchangethis.data.storage.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE email LIKE :email AND password LIKE :password")
    fun getUserByEmailAmdPassword(email: String, password: String): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Query("UPDATE users SET fullName =:fullName, phone =:phone, password =:password WHERE email LIKE :email")
    fun updateUser(
        fullName: String,
        phone: String,
        password: String,
        email: String
    )

    @Query("SELECT * FROM users WHERE email LIKE :email")
    fun getUserByEmail(email: String): List<UserEntity>
}