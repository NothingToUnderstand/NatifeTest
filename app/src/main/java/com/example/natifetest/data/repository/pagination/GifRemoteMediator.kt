package com.example.natifetest.data.repository.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.natifetest.data.database.entity.GifEntity
import com.example.natifetest.data.network.Status
import com.example.natifetest.data.repository.LocalRepository
import com.example.natifetest.data.repository.RemoteRepository
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class GifRemoteMediator(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : RemoteMediator<Int, GifEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GifEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> state.pages.lastIndex
        }
        Timber.d("Loading page $page")

        return when (val response = remoteRepository.getGifsInTrends(15, page)) {
            is Status.Success -> {
                response.data.data?.let { localRepository.upsertAll(it) }
                val pagination = response.data.pagination
                MediatorResult.Success(
                    endOfPaginationReached = (pagination.offset * pagination.count) > pagination.totalCount
                )
            }
            is Status.Error -> MediatorResult.Error(IllegalStateException(response.errorMessage))
        }
    }
}