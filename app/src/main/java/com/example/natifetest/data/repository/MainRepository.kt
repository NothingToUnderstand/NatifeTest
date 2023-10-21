package com.example.natifetest.data.repository

import com.example.natifetest.data.model.Gif
import com.example.natifetest.data.model.PagedResponse
import com.example.natifetest.data.model.Pagination
import com.example.natifetest.data.network.Status

interface MainRepository {
   suspend fun getGifsInTrends(pageSize: Int, offset: Int): Status<out PagedResponse<Gif, Pagination>>
   suspend fun searchGifs(searchQuery: String, pageSize: Int, offset: Int):  Status<out PagedResponse<Gif, Pagination>>
}