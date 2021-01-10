package com.example.gamecounter.db.repository

import androidx.annotation.WorkerThread
import com.example.gamecounter.db.dao.GameDao
import com.example.gamecounter.db.model.Game
import kotlinx.coroutines.flow.Flow

class GameRepository(private val gameDao: GameDao) {


    val allGames: Flow<List<Game>> = gameDao.getAlphebetizedNames()


    @WorkerThread
    suspend fun insert(game: Game){
        gameDao.insert(game)
    }
}