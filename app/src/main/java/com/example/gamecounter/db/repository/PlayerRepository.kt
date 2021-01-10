package com.example.gamecounter.db.repository

import androidx.annotation.WorkerThread
import com.example.gamecounter.db.dao.PlayerDao
import com.example.gamecounter.db.model.Player
import kotlinx.coroutines.flow.Flow

class PlayerRepository(private val playerDao: PlayerDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allPlayers: Flow<List<Player>> = playerDao.getAlphebetizedNames()

    val first: Player? = playerDao.first()


    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insert(player: Player) {
        playerDao.insert(player)
    }
}