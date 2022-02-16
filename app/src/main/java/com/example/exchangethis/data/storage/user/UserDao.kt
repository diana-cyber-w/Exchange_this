package com.example.exchangethis.data.storage.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE email =:email AND password =:password")
    fun getUserByEmailAmdPassword(email: String, password: String): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

}