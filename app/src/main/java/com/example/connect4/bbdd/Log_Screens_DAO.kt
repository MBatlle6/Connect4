package com.example.connect4.bbdd;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import kotlinx.coroutines.flow.Flow;


@Dao
interface Log_Screens_DAO {

    @Query("SELECT * FROM Strings_table ORDER BY word ASC")
    fun getAlphabetizedWords(): Flow<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Log_Strings)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}