package com.example.natifetest.data.repository

import androidx.paging.PagingSource
import com.example.natifetest.data.database.entity.GifEntity
import com.example.natifetest.data.model.Gif

interface LocalRepository {
    suspend fun upsert(gif: Gif)
    suspend fun upsertAll(gifs: List<Gif>)
    fun getPagedGifs(): PagingSource<Int, GifEntity>
    suspend fun drop()
}