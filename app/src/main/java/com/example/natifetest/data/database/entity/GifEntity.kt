package com.example.natifetest.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = GifEntity.TABLE_NAME)
data class GifEntity(
    @PrimaryKey
    val id:String,
    val url:String,
){
    companion object {
        const val TABLE_NAME = "GifEntity"
    }
}