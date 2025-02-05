package com.example.capstoneexpert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListNewsResponse(
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("totalResults")
    val totalResults: Int,

    @field:SerializedName("articles")
    val articles: List<NewsResponse>
)