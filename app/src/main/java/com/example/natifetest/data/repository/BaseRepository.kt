package com.example.natifetest.data.repository


import com.example.natifetest.data.network.ResponseHandler
import com.example.natifetest.data.network.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


open class BaseRepository : ResponseHandler() {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Status<out T> {
        return withContext(Dispatchers.IO) {
            try {
                handleSuccess(apiCall.invoke())
            } catch (e: Exception) {
                Timber.tag("safe api call exception").e(e.stackTraceToString())
                handleException(e)
            }
        }
    }
}