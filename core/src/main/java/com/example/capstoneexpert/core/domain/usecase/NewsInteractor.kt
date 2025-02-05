package com.example.capstoneexpert.core.domain.usecase

import com.example.capstoneexpert.core.domain.model.News
import com.example.capstoneexpert.core.domain.repository.INewsRepository

class NewsInteractor(private val newsRepository: INewsRepository): NewsUseCase {
    override fun getAllNews() = newsRepository.getAllNews()
    override fun getFavoriteNews() = newsRepository.getFavoriteNews()
    override suspend fun setFavoriteNews(news: News, state: Boolean) =
        newsRepository.setFavoriteNews(news, state)
}