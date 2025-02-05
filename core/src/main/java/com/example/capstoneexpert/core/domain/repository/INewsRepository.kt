package com.example.capstoneexpert.core.domain.repository

import com.example.capstoneexpert.core.data.Resource
import com.example.capstoneexpert.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun getAllNews(): Flow<Resource<List<News>>>
    fun getFavoriteNews(): Flow<List<News>>
    suspend fun setFavoriteNews(news: News, state: Boolean)
}