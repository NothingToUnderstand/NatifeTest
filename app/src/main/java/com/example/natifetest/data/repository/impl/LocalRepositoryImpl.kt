package com.example.natifetest.data.repository.impl

import com.example.natifetest.data.database.dao.GifsDao
import com.example.natifetest.data.database.mapper.gifToEntity
import com.example.natifetest.data.model.Gif
import com.example.natifetest.data.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dao: GifsDao
) : LocalRepository {
    override suspend fun upsert(gif: Gif) = dao.upsert(gifToEntity(gif))

    override suspend fun upsertAll(gifs: List<Gif>) = dao.upsertAll(gifs.map { gifToEntity(it) })

    override fun getPagedGifs() = dao.getPaging()
    override suspend fun drop() = dao.drop()

}