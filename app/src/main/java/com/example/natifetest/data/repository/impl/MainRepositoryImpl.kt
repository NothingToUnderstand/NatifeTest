package com.example.natifetest.data.repository.impl

import com.example.natifetest.data.repository.BaseRepository
import com.example.natifetest.data.repository.MainRepository
import com.example.natifetest.data.repository.ProjectApi
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val projectApi: ProjectApi
) : MainRepository, BaseRepository() {

    override suspend fun getGifsInTrends(pageSize: Int, offset: Int) = safeApiCall {
        projectApi.getGifsInTrends(pageSize, offset)
    }

    override suspend fun searchGifs(searchQuery: String, pageSize: Int, offset: Int) = safeApiCall {
        projectApi.searchGifs(searchQuery, pageSize, offset)
    }
}