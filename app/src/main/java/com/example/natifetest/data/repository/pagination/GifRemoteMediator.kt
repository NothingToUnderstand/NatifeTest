package com.example.natifetest.data.repository.pagination

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.natifetest.data.database.AppDatabase
import com.example.natifetest.data.database.entity.GifEntity
import com.example.natifetest.data.network.Status
import com.example.natifetest.data.repository.LocalRepository
import com.example.natifetest.data.repository.RemoteRepository
import timber.log.Timber
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class GifRemoteMediator @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val appDataBase: AppDatabase
) : RemoteMediator<Int, GifEntity>() {

    private var offset = 1
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GifEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> offset
        }
        Timber.d("Loading page $page")
        return when (val response = remoteRepository.getGifsInTrends(state.config.pageSize, page)) {
            is Status.Success -> {
                response.data.data?.let {
                    appDataBase.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            localRepository.drop()
                        }
                        localRepository.upsertAll(it)
                    }
                }
                val pagination = response.data.pagination
                offset = pagination.offset + 1
                MediatorResult.Success(
                    endOfPaginationReached = (pagination.offset * pagination.count) > pagination.totalCount
                )
            }

            is Status.Error -> MediatorResult.Error(IllegalStateException(response.errorMessage))
        }
    }
}