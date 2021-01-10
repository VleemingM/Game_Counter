package com.example.gamecounter.db.repository

import androidx.annotation.WorkerThread
import com.example.gamecounter.db.dao.RoundDao
import com.example.gamecounter.db.model.Player
import com.example.gamecounter.db.model.Round
import kotlinx.coroutines.flow.Flow

class RoundRepository(private val roundDao: RoundDao) {

    val allRounds: Flow<List<Round>> = roundDao.getAllSortedByPlayer()
    @WorkerThread
    suspend fun insert(round: Round) {
        roundDao.insert(round)
    }
}