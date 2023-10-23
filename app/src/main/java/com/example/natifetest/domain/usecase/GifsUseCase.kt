package com.example.natifetest.domain.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.filter
import androidx.paging.map
import com.example.natifetest.data.database.AppDatabase
import com.example.natifetest.data.database.mapper.gifFromEntity
import com.example.natifetest.data.repository.LocalRepository
import com.example.natifetest.data.repository.RemoteRepository
import com.example.natifetest.data.repository.pagination.GifRemoteMediatorSearch
import com.example.natifetest.utils.helpers.SharedPrefHelper
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GifsUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository,
    private val appDataBase: AppDatabase,
    private val sharedPrefHelper: SharedPrefHelper,
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getGifsSearch(search: String) = Pager(
        config = PagingConfig(PAGE_SIZE, initialLoadSize = PAGE_SIZE),
        remoteMediator = GifRemoteMediatorSearch(
            remoteRepository,
            localRepository,
            appDataBase,
            search
        ),
        pagingSourceFactory = { localRepository.getPagedGifs() }
    ).flow.map { pagingData ->
        pagingData.map {
            gifFromEntity(it)
        }.filter {
            sharedPrefHelper.deletedIds?.contains(it.id)?.not() ?: true
        }
    }
    companion object {
        private const val PAGE_SIZE = 25
    }

}