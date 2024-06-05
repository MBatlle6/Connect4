package com.example.connect4.bbdd;

// Annotates class to be a Room Database with a table (entity) of the Word class

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlin.jvm.Volatile;

@Database(entities = [LogStrings::class], version = 2)
abstract class LogDataBase : RoomDatabase() {

    abstract fun dao(): LogScreensDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LogDataBase? = null

        fun getDataBase(context: Context, scope: CoroutineScope): LogDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LogDataBase::class.java,
                    "log_database"
                )
                    .addCallback(LogDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
        private class LogDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.dao())
                    }
                }
            }
        }
        suspend fun populateDatabase(wordDao: LogScreensDAO) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            wordDao.deleteAll()
        }
    }

}