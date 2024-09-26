package com.aashishtathod.dev.quotesapp.domain.repository

import androidx.paging.PagingData
import com.aashishtathod.dev.quotesapp.domain.model.QuoteModel
import com.aashishtathod.dev.quotesapp.util.APIResponse
import kotlinx.coroutines.flow.Flow

interface QuotesRepository {

    fun getQuoteList(): Flow<PagingData<QuoteModel>>
}