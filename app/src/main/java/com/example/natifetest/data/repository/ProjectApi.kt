package com.example.natifetest.data.repository

import com.example.natifetest.data.model.Gif
import com.example.natifetest.data.model.PagedResponse
import com.example.natifetest.data.model.Pagination
import retrofit2.http.GET
import retrofit2.http.Query

interface ProjectApi {
    @GET("search")
    suspend fun searchGifs(
        @Query(QUERY_SEARCH) search: String,
        @Query(LIMIT) pageSize: Int,
        @Query(OFFSET) offset: Int,
    ): PagedResponse<Gif, Pagination?>

    companion object {
        private const val LIMIT = "limit"
        private const val OFFSET = "offset"
        private const val QUERY_SEARCH = "q"
    }
}