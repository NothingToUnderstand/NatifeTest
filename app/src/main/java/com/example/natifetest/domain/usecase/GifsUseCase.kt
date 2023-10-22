package com.example.natifetest.domain.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.example.natifetest.data.database.mapper.gifFromEntity
import com.example.natifetest.data.repository.LocalRepository
import com.example.natifetest.data.repository.RemoteRepository
import com.example.natifetest.data.repository.pagination.GifRemoteMediator
import com.example.natifetest.data.repository.pagination.GifsPagingSource
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GifsUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) {
    //    fun getGifs() = Pager(
//        config = PagingConfig(10, initialLoadSize = 10),
//        pagingSourceFactory = {
//            GifsPagingSource(remoteRepository)
//        }
//    ).flow
    @OptIn(ExperimentalPagingApi::class)
    fun getGifs() = Pager(
        config = PagingConfig(10, initialLoadSize = 10),
        remoteMediator = GifRemoteMediator(
            remoteRepository,
            localRepository
        ),
        pagingSourceFactory = { localRepository.getPagedGifs() }
    ).flow.map { pagingData ->
        pagingData.map {
            gifFromEntity(it)
        }
    }

}