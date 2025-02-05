package com.example.capstoneexpert.core.utils

import com.example.capstoneexpert.core.data.source.local.entity.NewsEntity
import com.example.capstoneexpert.core.data.source.remote.response.NewsResponse
import com.example.capstoneexpert.core.domain.model.News

object DataMapper {
    fun mapResponsesToEntities(input: List<NewsResponse>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                url = it.url,
                description = it.description,
                title = it.title,
                urlToImage = it.urlToImage,
                sourceName = it.source.toString(),
                publishedAt = it.publishedAt,
                content = it.content,
                author = it.author,
                isFavorite = false
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> =
        input.map {
            News(
                url = it.url,
                description = it.description,
                title = it.title,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content,
                author = it.author,
                sourceName = it.sourceName,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: News) = NewsEntity(
        url = input.url,
        description = input.description,
        title = input.title,
        urlToImage = input.urlToImage,
        publishedAt = input.publishedAt,
        content = input.content,
        author = input.author,
        sourceName = input.sourceName,
        isFavorite = input.isFavorite
    )
}