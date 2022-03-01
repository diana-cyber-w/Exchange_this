package com.example.exchangethis.network.di

import com.example.exchangethis.network.RetrofitClient
import com.example.exchangethis.network.repository.NetworkRepositoryImpl
import org.koin.dsl.module

val networkModule = module {
    single {
        RetrofitClient.getBookApi()
    }

    single {
        NetworkRepositoryImpl(bookApi = get())
    }
}