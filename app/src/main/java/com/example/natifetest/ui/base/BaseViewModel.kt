package com.example.natifetest.ui.base

import androidx.lifecycle.ViewModel
import com.example.natifetest.data.network.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart


abstract class BaseViewModel : ViewModel() {

    fun <T> makeApiCall(apiCall: suspend () -> Status<T>): Flow<Status<out T>> =
        flow<Status<out T>> { emit(apiCall.invoke()) }
            .flowOn(Dispatchers.IO)
            .onStart { emit(Status.Loading) }
}