package com.example.gamecounter.db.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RoundWithPlayers (
    @Embedded val round: Round,
    @Relation(
        parentColumn = "roundId",
        entityColumn = "playerId",
        associateBy = Junction(PlayerRoundCrossRef::class)
    )
    val players: List<Player>
)