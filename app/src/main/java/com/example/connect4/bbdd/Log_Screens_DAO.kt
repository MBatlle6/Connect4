package com.example.connect4.bbdd;

import androidx.lifecycle.LiveData
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.concurrent.Flow
import com.example.connect4.data.SettingsDataStore


@Dao
interface Log_Screens_DAO {

    @Query("SELECT * FROM Strings_table ORDER BY id ASC")
    fun getAlphabetizedWords(): LiveData<List<LogStrings>>//Flow<List<LogStrings>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: LogStrings)

    @Query("DELETE FROM Strings_table")
    suspend fun deleteAll()

    companion object
}