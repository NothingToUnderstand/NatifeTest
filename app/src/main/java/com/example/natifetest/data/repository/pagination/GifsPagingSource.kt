package com.example.natifetest.data.repository.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.natifetest.data.model.Gif
import com.example.natifetest.data.network.Status
import com.example.natifetest.data.repository.RemoteRepository

class GifsPagingSource(
    private val remoteRepository: RemoteRepository,
) : PagingSource<Int, Gif>() {

    override fun getRefreshKey(state: PagingState<Int, Gif>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gif> {
        val position = params.key ?: 0

        return when (val response = remoteRepository.getGifsInTrends(params.loadSize, position)) {
            is Status.Success -> {
                LoadResult.Page(
                    data = response.data.data ?: emptyList(),
                    prevKey = null,
                    nextKey = position + 1
                )
            }

            is Status.Error -> LoadResult.Error(response.exception)
            else -> LoadResult.Error(Exception())
        }
    }
}