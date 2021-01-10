package com.example.gamecounter.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true) val gameId: Long = 0L,
    var name: String
    )
