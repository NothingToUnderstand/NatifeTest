package com.example.natifetest.data.database.mapper

import com.example.natifetest.data.database.entity.GifEntity
import com.example.natifetest.data.model.Gif

fun gifToEntity(gif: Gif): GifEntity {
    return GifEntity(
        gif.id,
        gif.url,
    )
}

fun gifFromEntity(gifEntity: GifEntity): Gif {
    return Gif(
        gifEntity.id,
        gifEntity.url,
    )
}
