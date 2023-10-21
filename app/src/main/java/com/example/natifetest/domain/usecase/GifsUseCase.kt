package com.example.natifetest.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.natifetest.data.repository.MainRepository
import com.example.natifetest.data.repository.pagination.GifsPagingSource
import javax.inject.Inject

class GifsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun getGifs() = Pager(
        config = PagingConfig(10, initialLoadSize = 10),
        pagingSourceFactory = {
            GifsPagingSource(mainRepository)
        }
    ).flow

}