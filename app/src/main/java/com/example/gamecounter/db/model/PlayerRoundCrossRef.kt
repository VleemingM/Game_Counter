package com.example.gamecounter.db.model

import androidx.room.*

@Entity(
    primaryKeys = ["roundId", "playerId"],
)
data class PlayerRoundCrossRef(
    val roundId: Long,
    val playerId: Long
)


