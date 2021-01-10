package com.example.gamecounter.db.dao

import androidx.room.*
import com.example.gamecounter.db.model.*
import kotlinx.coroutines.flow.Flow


@Dao
interface PlayerRoundDao {

    //    @Transaction
//    @Query("SELECT * FROM Player")
//    fun getRoundWithPlayers(): List<RoundWithPlayers>
//
    @Transaction
    @Query("SELECT * FROM Round where roundId = :roundId")
    fun getRoundsFromPlayer(roundId: Long): Flow<List<RoundWithPlayers>>


    @Transaction
    @Query("SELECT * FROM Player where playerId = :playerId")
    fun getPlayersFromRound(playerId: Long): Flow<List<PlayerWithRounds>>

}