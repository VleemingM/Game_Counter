package com.example.gamecounter.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gamecounter.db.model.Game
import com.example.gamecounter.db.model.Player
import kotlinx.coroutines.flow.Flow


@Dao
interface GameDao {
    @Query("SELECT * FROM Game ORDER BY name ASC")
    fun getAlphebetizedNames(): Flow<List<Game>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: Game)

    @Query("DELETE FROM Game")
    suspend fun deleteAll()

}