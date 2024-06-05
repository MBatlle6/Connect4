package com.example.connect4.bbdd;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
//import java.util.concurrent.Flow
import kotlinx.coroutines.flow.Flow


@Dao
interface LogScreensDAO {
    @Query("SELECT * FROM Strings_table ORDER BY id ASC")
    fun getLogs(): Flow<List<LogStrings>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: LogStrings)

}