package com.example.natifetest.data.model

import com.google.gson.annotations.SerializedName

data class PagedResponse<T, U>(
    val data: List<T>?,
    val pagination: U,
)

data class Pagination(
    @SerializedName("total_count")
    val totalCount: Int,
    val count: Int,
    val offset: Int
)