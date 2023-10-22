package com.example.natifetest.ui.first_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.natifetest.data.model.Gif
import com.example.natifetest.domain.usecase.GifsUseCase
import com.example.natifetest.utils.helpers.SharedPrefHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor(
    private val gifsUseCase: GifsUseCase,
    private val sharedPrefHelper: SharedPrefHelper
) : ViewModel() {

    private val _gifs = MutableStateFlow<PagingData<Gif>?>(null)
    val gifs = _gifs.asStateFlow()
    fun getGifs() {
        viewModelScope.launch {
            gifsUseCase.getGifs().cachedIn(viewModelScope).collect {
                _gifs.value = it
            }
        }
    }

    fun deleteGif(gifId: String) {
        sharedPrefHelper.addToSetDeletedIds(gifId)
        _gifs.update {
            it?.filter { gif ->
                gif.id != gifId
            }
        }
    }
}