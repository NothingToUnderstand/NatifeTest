package com.example.natifetest.data.repository.impl

import com.example.natifetest.data.repository.BaseRepository
import com.example.natifetest.data.repository.RemoteRepository
import com.example.natifetest.data.repository.ProjectApi
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val projectApi: ProjectApi
) : RemoteRepository, BaseRepository() {

    override suspend fun searchGifs(searchQuery: String, pageSize: Int, offset: Int) = safeApiCall {
        projectApi.searchGifs(searchQuery, pageSize, offset)
    }
}