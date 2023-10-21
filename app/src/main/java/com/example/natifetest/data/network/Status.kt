package com.example.natifetest.data.network

sealed class Status<T> {
    data class Success<T>(val data: T) : Status<T>()
    data class Error(val errorMessage: String, val exception: Exception) : Status<Nothing>()
    data object Loading : Status<Nothing>()
}