package com.example.exchangethis.data.di

import androidx.room.Room
import com.example.exchangethis.data.repository.DataRepositoryImpl
import com.example.exchangethis.data.storage.AppDatabase
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "users"
        ).build()
    }

    single {
        get<AppDatabase>().getUserDao()
    }

    single {
        DataRepositoryImpl(userDao = get())
    }
}