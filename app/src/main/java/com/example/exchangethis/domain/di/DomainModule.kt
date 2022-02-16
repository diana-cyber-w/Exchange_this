package com.example.exchangethis.domain.di

import com.example.exchangethis.domain.UserInteractor
import com.example.exchangethis.domain.UserInteractorImpl
import org.koin.dsl.module

val domainModule = module {
    single<UserInteractor> {
        UserInteractorImpl(dataRepositoryImpl = get())
    }
}