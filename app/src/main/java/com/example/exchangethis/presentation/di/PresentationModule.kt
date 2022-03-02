package com.example.exchangethis.presentation.di

import com.example.exchangethis.presentation.viewModels.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        UserViewModel(userInteractor = get())
    }

    viewModel {
        MyBookViewModel(bookInteractor = get(), get())
    }

    viewModel {
        LibraryViewModel(bookInteractor = get())
    }

    viewModel {
        FavouriteBookViewModel(bookInteractor = get())
    }

    viewModel {
        CategoryViewModel()
    }
}
