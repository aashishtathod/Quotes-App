package com.aashishtathod.dev.quotesapp.data.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aashishtathod.dev.quotesapp.data.source.paging.QuotesPagingDataSource
import com.aashishtathod.dev.quotesapp.data.source.remote.service.QuotesAPI
import com.aashishtathod.dev.quotesapp.domain.model.QuoteModel
import com.aashishtathod.dev.quotesapp.domain.repository.QuotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuotesRepositoryImpl @Inject constructor(
    private val api: QuotesAPI
) : QuotesRepository {

    override fun getQuoteList(): Flow<PagingData<QuoteModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                QuotesPagingDataSource(api)
            }
        ).flow

    }
}