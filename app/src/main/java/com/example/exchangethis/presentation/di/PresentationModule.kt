package com.example.exchangethis.presentation.di

import com.example.exchangethis.presentation.viewModels.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module {
    viewModel {
        UserViewModel(userInteractor = get())
    }
}
