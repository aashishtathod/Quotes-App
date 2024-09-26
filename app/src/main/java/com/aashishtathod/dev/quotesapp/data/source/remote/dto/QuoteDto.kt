package com.aashishtathod.dev.quotesapp.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class QuoteDto(
    @SerializedName("count") val pageItemCount: Int,
    val page: Int,
    val totalPages: Int,
    val results: List<Quote>
)

data class Quote(
    @SerializedName("city")
    val author: String,
    @SerializedName("name")
    val content: String
)