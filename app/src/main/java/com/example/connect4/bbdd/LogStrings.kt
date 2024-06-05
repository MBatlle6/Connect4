package com.example.connect4.bbdd


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.time.LocalDateTime

@Entity(tableName = "Strings_table")
class LogStrings(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val data: String,
    val currentTime:String,
    val alias :String

)