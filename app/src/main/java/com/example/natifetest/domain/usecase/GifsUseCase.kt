package com.example.natifetest.domain.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.filter
import androidx.paging.map
import com.example.natifetest.data.database.mapper.gifFromEntity
import com.example.natifetest.data.repository.LocalRepository
import com.example.natifetest.data.repository.RemoteRepository
import com.example.natifetest.data.repository.pagination.GifRemoteMediator
import com.example.natifetest.data.repository.pagination.GifsPagingSource
import com.example.natifetest.utils.helpers.SharedPrefHelper
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GifsUseCase @Inject constructor(
    private val gifRemoteMediator: GifRemoteMediator,
    private val localRepository: LocalRepository,
    private val sharedPrefHelper: SharedPrefHelper
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getGifs() = Pager(
        config = PagingConfig(25, initialLoadSize = 25),
        remoteMediator = gifRemoteMediator,
        pagingSourceFactory = { localRepository.getPagedGifs() }
    ).flow.map { pagingData ->
        pagingData.map {
            gifFromEntity(it)
        }.filter {
            sharedPrefHelper.deletedIds?.contains(it.id)?.not() ?: true
        }
    }

}