package com.example.gamecounter.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor

@Entity
data class Player(

    @PrimaryKey(autoGenerate = true)
    val playerId: Long = 0L,


    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "age")
    val age: Int

)