package com.example.gamecounter.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gamecounter.db.model.Player
import kotlinx.coroutines.flow.Flow


@Dao
interface PlayerDao {

    @Query("SELECT * FROM Player ORDER BY name ASC")
    fun getAlphebetizedNames(): Flow<List<Player>>

    @Query("SELECT * FROM Player ORDER BY age ASC")
    fun getNumerizedAges(): List<Player>

    @Query("SELECT * FROM Player ORDER BY playerId ASC LIMIT 1")
    fun first(): Player?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(player: Player)

    @Query("DELETE FROM Player")
    suspend fun deleteAll()


}