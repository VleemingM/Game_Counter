package com.example.gamecounter.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.gamecounter.db.model.GameWithRounds

@Dao
interface GameWithRoundsDao {
    @Transaction
    @Query("SELECT * FROM Game")
    fun getGamesWithRounds(): List<GameWithRounds>
}