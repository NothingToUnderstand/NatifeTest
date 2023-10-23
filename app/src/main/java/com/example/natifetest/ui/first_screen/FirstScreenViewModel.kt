package com.example.natifetest.ui.first_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.natifetest.data.model.Gif
import com.example.natifetest.data.repository.LocalRepository
import com.example.natifetest.domain.usecase.GifsUseCase
import com.example.natifetest.utils.helpers.SharedPrefHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FirstScreenViewModel @Inject constructor(
    private val gifsUseCase: GifsUseCase,
    private val sharedPrefHelper: SharedPrefHelper,
) : ViewModel() {

    private val _gifs = MutableStateFlow<PagingData<Gif>?>(null)
    val gifs = _gifs.asStateFlow()
    private var searchJob: Job? = null


    fun getGifsSearch(search: String) {
        Timber.d("Get gifs $search")
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            gifsUseCase.getGifsSearch(search).cachedIn(viewModelScope).collectLatest {
                _gifs.value = it
            }
        }
    }

    fun deleteGif(gifId: String, forever: Boolean) {
        Timber.d("Gif deleted")
        if (forever) {
            sharedPrefHelper.addToSetDeletedIds(gifId)
        }
        _gifs.update {
            it?.filter { gif ->
                gif.id != gifId
            }
        }
    }
}