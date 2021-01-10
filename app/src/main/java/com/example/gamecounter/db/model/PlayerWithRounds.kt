package com.example.gamecounter.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation



data class PlayerWithRounds(
    @Embedded val player: Player,
    @Relation(
        parentColumn = "playerId",
        entityColumn = "roundId",
        associateBy = Junction(PlayerRoundCrossRef::class)
    )
    val rounds: List<Round>
)
