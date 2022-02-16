package com.example.exchangethis.utils.di

import com.example.exchangethis.utils.preference.SharedPreferenceManager
import com.example.exchangethis.utils.preference.SharedPreferenceManagerImpl
import org.koin.dsl.module

val utilsModule = module {
    single<SharedPreferenceManager> {
        SharedPreferenceManagerImpl(context = get())
    }
}