package com.example.gamecounter.db.model

import androidx.room.*

@Entity
data class Round(
    @PrimaryKey(autoGenerate = true) val roundId: Long = 0L,
    val gameId: Long,
    val winningPlayerId: Long? = null
)

// TODO: 09/01/2021 Check how to combine Round with Players and Round with game also add a winning player.
