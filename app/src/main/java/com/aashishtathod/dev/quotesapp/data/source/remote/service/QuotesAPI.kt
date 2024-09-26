package com.aashishtathod.dev.quotesapp.data.source.remote.service

import com.aashishtathod.dev.quotesapp.data.source.remote.dto.Quote
import com.aashishtathod.dev.quotesapp.data.source.remote.dto.QuoteDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesAPI {

    companion object {
        const val BASE_URL = "https://api.openbrewerydb.org/v1/"
        const val QUOTES_API = "breweries"

    }

    @GET(QUOTES_API)
    suspend fun getQuoteList(
        @Query("page") pageNumber: Int,
        @Query("per_page") perPageLimit: Int = 10
    ): List<Quote>
}