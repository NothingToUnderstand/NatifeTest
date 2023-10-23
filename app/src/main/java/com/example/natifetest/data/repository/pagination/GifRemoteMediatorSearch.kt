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

@OptIn(ExperimentalPagingApi::class)
class GifRemoteMediatorSearch(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val appDataBase: AppDatabase,
    private val search: String
) : RemoteMediator<Int, GifEntity>() {

    private var offset = 0
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GifEntity>
    ): MediatorResult {

        val offset = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> offset

        }
        Timber.d("Loading offset $offset")

        val response = if (search.isEmpty().not()) {
            remoteRepository.searchGifs(search, state.config.pageSize, offset)
        } else return MediatorResult.Success(endOfPaginationReached = true)

        Timber.d("Response $response")

        return when (response) {
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
                pagination?.offset?.plus(state.config.pageSize)?.let {
                    this.offset = it
                }
                MediatorResult.Success(
                    endOfPaginationReached = pagination != null && pagination.offset >= pagination.totalCount
                )
            }

            is Status.Error -> MediatorResult.Error(IllegalStateException(response.errorMessage))
        }
    }
}