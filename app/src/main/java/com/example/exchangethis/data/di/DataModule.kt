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
            "usersAndBooks"
        ).build()
    }

    single {
        get<AppDatabase>().getUserDao()
    }

    single {
        get<AppDatabase>().getBookDao()
    }

    single {
        DataRepositoryImpl(userDao = get(), bookDao = get())
    }
}