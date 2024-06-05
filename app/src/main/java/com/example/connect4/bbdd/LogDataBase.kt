package com.example.connect4.bbdd

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LogStrings::class], version = 1)
abstract class LogDataBase : RoomDatabase(){
    abstract val dao: LogScreensDAO
}