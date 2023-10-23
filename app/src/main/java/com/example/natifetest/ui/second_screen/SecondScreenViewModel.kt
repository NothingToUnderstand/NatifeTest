package com.example.natifetest.ui.second_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.natifetest.data.model.Gif
import com.example.natifetest.domain.usecase.GifsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SecondScreenViewModel @Inject constructor(
    private val gifsUseCase: GifsUseCase,
) : ViewModel() {
    private val _gifs = MutableStateFlow<PagingData<Gif>?>(null)
    val gifs = _gifs.asStateFlow()

    fun getGifs() {
        viewModelScope.launch {
            gifsUseCase.getGifs(null).cachedIn(viewModelScope).collect {
                _gifs.value = it
            }
        }
    }
}