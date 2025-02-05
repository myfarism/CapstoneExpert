package com.example.capstoneexpert.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val url: String,
    val title: String,
    val author: String?,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val isFavorite: Boolean = false
) : Parcelable