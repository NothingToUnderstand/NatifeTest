package com.example.natifetest.ui.second_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.natifetest.data.model.Gif
import com.example.natifetest.domain.usecase.GifsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class SecondScreenViewModel @Inject constructor(
    private val gifsUseCase: GifsUseCase,
) : ViewModel() {

    private val _gifs = MutableStateFlow<PagingData<Gif>?>(null)
    val gifs = _gifs.asStateFlow()

    fun getGifs(search: String) {
        viewModelScope.launch {
            gifsUseCase.getGifsSearch(search).cachedIn(viewModelScope).collect {
                _gifs.value = it
            }
        }
    }
    fun deleteGif(gifId: String) {
        Timber.d("Gif deleted")
        _gifs.update {
            it?.filter { gif ->
                gif.id != gifId
            }
        }
    }
}