package com.example.natifetest.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.natifetest.data.database.entity.GifEntity

@Dao
interface GifsDao {

    @Upsert
    suspend fun upsert(gif: GifEntity)

    @Upsert
    suspend fun upsertAll(gifs: List<GifEntity>)

    @Query("SELECT * FROM ${GifEntity.TABLE_NAME}")
    fun getPaging(): PagingSource<Int, GifEntity>

    @Query("DELETE FROM ${GifEntity.TABLE_NAME}")
    suspend fun drop()

}