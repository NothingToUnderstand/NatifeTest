package com.example.natifetest.data.network

import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException

open class ResponseHandler {
    fun <T> handleSuccess(data: T): Status<T> {
        Timber.i("Success response")
        return Status.Success(data)
    }

    fun <T : Any> handleException(e: Exception): Status<out T> {
        Timber.tag("response handler exception").e(e.stackTraceToString())
         return when (e) {
                is SocketTimeoutException -> Status.Error("Timeout",e)
                is IOException -> Status.Error("No internet connection",e)
                else -> Status.Error("Something went wrong",e)
            }

    }
}