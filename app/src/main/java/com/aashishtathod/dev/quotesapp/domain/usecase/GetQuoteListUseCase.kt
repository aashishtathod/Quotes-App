package com.aashishtathod.dev.quotesapp.domain.usecase

import androidx.paging.PagingData
import com.aashishtathod.dev.quotesapp.domain.model.QuoteModel
import com.aashishtathod.dev.quotesapp.domain.repository.QuotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuoteListUseCase @Inject constructor(
    private val repository: QuotesRepository
) {

    operator fun invoke(): Flow<PagingData<QuoteModel>> {
        return repository.getQuoteList()
    }
}