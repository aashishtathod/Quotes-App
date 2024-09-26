package com.aashishtathod.dev.quotesapp.util

sealed class APIResponse<out T> {

    object Loading : APIResponse<Nothing>()
    data class Success<out T>(val data: T) : APIResponse<T>()
    data class Failure(val errorMsg: String) : APIResponse<Nothing>()
}