package com.example.gamecounter.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
class GameWithRounds(
    @Embedded
    val game: Game,

    @Relation(
        parentColumn = "gameId",
        entityColumn = "gameId"
    )
    val rounds: List<Round>
)