package com.aashishtathod.dev.quotesapp.ui

import androidx.paging.PagingData
import com.aashishtathod.dev.quotesapp.domain.model.QuoteModel

sealed class QuoteUiState {
    object Loading : QuoteUiState()
    object None : QuoteUiState()
    data class Success(val data: PagingData<QuoteModel>) : QuoteUiState()
    data class Error(val errorMsg: String) : QuoteUiState()
}