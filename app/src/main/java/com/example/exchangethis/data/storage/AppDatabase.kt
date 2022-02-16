package com.example.exchangethis.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.exchangethis.data.storage.user.UserDao
import com.example.exchangethis.data.storage.user.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = AppDatabase.VERSION
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val VERSION = 1
    }

    abstract fun getUserDao(): UserDao
}