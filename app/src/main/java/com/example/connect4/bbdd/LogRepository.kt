package com.example.connect4.bbdd

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import java.util.concurrent.Flow

class LogRepository(private val LogScreensDAO: Log_Screens_DAO) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: LiveData<List<LogStrings>> = LogScreensDAO.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(logStrings: LogStrings) {
        LogScreensDAO.insert(logStrings)
    }
}

