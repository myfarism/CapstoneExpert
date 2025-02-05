package com.example.capstoneexpert.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstoneexpert.core.domain.usecase.NewsUseCase

class FavViewModel(newsUseCase: NewsUseCase) : ViewModel() {
    val favNews = newsUseCase.getFavoriteNews().asLiveData()
}

