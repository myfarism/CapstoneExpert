package com.example.capstoneexpert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SourceResponse(
    @field:SerializedName("id")
    val id: String?,

    @field:SerializedName("name")
    val name: String
)