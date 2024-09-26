package com.aashishtathod.dev.quotesapp.domain.mapper

import com.aashishtathod.dev.quotesapp.data.source.remote.dto.Quote
import com.aashishtathod.dev.quotesapp.domain.model.QuoteModel

fun List<Quote>.toQuoteModelList(): List<QuoteModel> {
    return this.map { quoteDto ->
        QuoteModel(
            author = quoteDto.author,
            content = quoteDto.content
        )
    }
}