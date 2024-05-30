package com.example.connect4.bbdd


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Strings_table")
class Log_Strings(

    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "key") val key: String

)