package com.example.natifetest.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = GifEntity.TABLE_NAME)
data class GifEntity(
    @PrimaryKey
    val id:String,
    val url:String,
    val deleted:Boolean
){
    companion object {
        const val TABLE_NAME = "GifEntity"
    }
}