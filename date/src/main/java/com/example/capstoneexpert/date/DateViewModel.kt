package com.example.capstoneexpert.date

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstoneexpert.core.domain.usecase.NewsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class DateViewModel(newsUseCase: NewsUseCase) : ViewModel() {
    private val _category = MutableStateFlow("business")
    @OptIn(ExperimentalCoroutinesApi::class)
    val news = _category
        .flatMapLatest { category ->
            newsUseCase.getAllNews(category)
        }
        .asLiveData()
}