package com.example.connect4.bbdd
import com.example.connect4.bbdd.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LogRepository(private val logDao: LogScreensDAO) {
    suspend fun getLog(): Flow<Log> {
        val entities = logDao.getLogs()
        return entities.map {
            Log(data = it.)
        }
    }

    suspend fun insertLog(log: Log){
        val entity = LogStrings(data = log.data)
        logDao.insert(entity)
    }
}