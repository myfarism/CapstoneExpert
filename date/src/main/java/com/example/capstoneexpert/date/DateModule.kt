package com.example.capstoneexpert.date

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dateModule = module {
    viewModel { DateViewModel(get()) }
}