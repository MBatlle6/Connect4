package com.example.connect4.bbdd

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.connect4.bbdd.LogStrings

class LogRepository(
    private val LogScreensDAO: LogScreensDAO) {

    val allLogs: Flow<List<LogStrings>> = LogScreensDAO.getLogs()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(logStrings: LogStrings) {
        LogScreensDAO.insert(logStrings)
    }
}

