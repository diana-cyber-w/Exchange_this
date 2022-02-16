package com.example.exchangethis

import android.app.Application
import com.example.exchangethis.data.di.dataModule
import com.example.exchangethis.domain.di.domainModule
import com.example.exchangethis.presentation.di.presentationModule
import com.example.exchangethis.utils.di.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(
                dataModule,
                domainModule,
                presentationModule,
                utilsModule
            )
        }
    }
}