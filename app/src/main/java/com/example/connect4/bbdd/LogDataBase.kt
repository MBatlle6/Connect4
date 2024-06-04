package com.example.connect4.bbdd;

// Annotates class to be a Room Database with a table (entity) of the Word class

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase

import kotlin.jvm.Volatile;

@Database(entities = arrayOf(LogStrings::class), version = 1, exportSchema = false)
public abstract class LogDataBase : RoomDatabase() {

    abstract fun Log_Screens_DAO(): Log_Screens_DAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LogDataBase? = null

        fun getDataBase(context: Context): LogDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LogDataBase::class.java,
                    "log_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}