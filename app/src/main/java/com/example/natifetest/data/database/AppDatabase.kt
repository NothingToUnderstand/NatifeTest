package com.example.natifetest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.natifetest.data.database.dao.GifsDao
import com.example.natifetest.data.database.entity.GifEntity

@Database(
    entities = [GifEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gifsDao(): GifsDao
    companion object{
        const val DATABASE_NAME = "gifs_database"
    }

}