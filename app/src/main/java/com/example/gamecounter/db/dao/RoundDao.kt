package com.example.gamecounter.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gamecounter.db.model.Player
import com.example.gamecounter.db.model.Round
import kotlinx.coroutines.flow.Flow

@Dao
interface RoundDao {

    @Query("SELECT * FROM Round ORDER BY winningPlayerId ASC")
     fun getAllSortedByPlayer(): Flow<List<Round>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(round: Round)

    @Query("DELETE FROM Round")
    suspend fun deleteAll()


}