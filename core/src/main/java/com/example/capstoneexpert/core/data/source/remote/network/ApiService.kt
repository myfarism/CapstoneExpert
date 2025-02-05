package com.example.capstoneexpert.core.data.source.remote.network

import com.example.capstoneexpert.core.data.source.remote.response.ListNewsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("top-headlines/category/business/gb.json")
    suspend fun getList(): ListNewsResponse
}