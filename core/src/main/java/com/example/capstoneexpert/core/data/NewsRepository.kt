package com.example.capstoneexpert.core.data

import com.example.capstoneexpert.core.data.source.local.LocalDataSource
import com.example.capstoneexpert.core.data.source.remote.RemoteDataSource
import com.example.capstoneexpert.core.data.source.remote.network.ApiResponse
import com.example.capstoneexpert.core.data.source.remote.response.NewsResponse
import com.example.capstoneexpert.core.domain.model.News
import com.example.capstoneexpert.core.domain.repository.INewsRepository
import com.example.capstoneexpert.core.utils.AppExecutors
import com.example.capstoneexpert.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class NewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : INewsRepository {
    override fun getAllNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<NewsResponse>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getAllNews().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<NewsResponse>>> =
                remoteDataSource.getAllNews()

            override suspend fun saveCallResult(data: List<NewsResponse>) {
                val newsList = DataMapper.mapResponsesToEntities(data)
                val existingNews = localDataSource.getAllNews().first()
                newsList.forEach { news ->
                    existingNews.find { it.url == news.url }?.let {
                        news.isFavorite = it.isFavorite
                    }
                }
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getFavoriteNews(): Flow<List<News>> {
        return localDataSource.getFavoriteNews().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setFavoriteNews(news: News, state: Boolean) {
        val newsEntity = DataMapper.mapDomainToEntity(news)
        localDataSource.setFavoriteNews(newsEntity, state)
    }
}