package com.example.connect4.bbdd


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Strings_table")
class LogStrings(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val valor: String

)