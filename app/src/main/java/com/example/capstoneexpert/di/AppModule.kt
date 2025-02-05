package com.example.capstoneexpert.di

import com.example.capstoneexpert.core.domain.usecase.NewsInteractor
import com.example.capstoneexpert.core.domain.usecase.NewsUseCase
import com.example.capstoneexpert.detail.DetailNewsViewModel
import com.example.capstoneexpert.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<NewsUseCase> { NewsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailNewsViewModel(get()) }
}