package com.aashishtathod.dev.quotesapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aashishtathod.dev.quotesapp.domain.usecase.GetQuoteListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val useCase: GetQuoteListUseCase
) : ViewModel() {

    init {
        getQuotes()
    }

    private val _uiState: MutableStateFlow<QuoteUiState> = MutableStateFlow(QuoteUiState.None)
    val uiState: StateFlow<QuoteUiState>
        get() = _uiState

    private fun getQuotes() {
        viewModelScope.launch {
            _uiState.emit(QuoteUiState.Loading)
            useCase.invoke()
                .cachedIn(viewModelScope)
                .collect {
                    _uiState.emit(QuoteUiState.Success(it))
                }

        }
    }
}