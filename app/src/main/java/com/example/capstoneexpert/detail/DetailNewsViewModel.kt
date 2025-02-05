package com.example.capstoneexpert.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneexpert.core.domain.model.News
import com.example.capstoneexpert.core.domain.usecase.NewsUseCase
import kotlinx.coroutines.launch

class DetailNewsViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun setFavoriteNews(news: News, newStatus:Boolean) = viewModelScope.launch {
        newsUseCase.setFavoriteNews(news, newStatus)
    }
}
