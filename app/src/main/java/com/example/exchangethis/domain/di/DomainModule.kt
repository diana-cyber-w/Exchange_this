package com.example.exchangethis.domain.di

import com.example.exchangethis.domain.interactors.BookInteractor
import com.example.exchangethis.domain.interactors.BookInteractorImpl
import com.example.exchangethis.domain.interactors.UserInteractor
import com.example.exchangethis.domain.interactors.UserInteractorImpl
import org.koin.dsl.module

val domainModule = module {
    single<UserInteractor> {
        UserInteractorImpl(dataRepositoryImpl = get())
    }

    single<BookInteractor> {
        BookInteractorImpl(dataRepositoryImpl = get(), networkRepositoryImpl = get())
    }
}