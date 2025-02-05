package com.example.capstoneexpert.core.domain.usecase

import com.example.capstoneexpert.core.data.Resource
import com.example.capstoneexpert.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllNews(): Flow<Resource<List<News>>>
    fun getFavoriteNews(): Flow<List<News>>
    suspend fun setFavoriteNews(news: News, state: Boolean)
}