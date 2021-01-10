package com.example.gamecounter

import android.app.Application
import com.example.gamecounter.db.GameCounterDatabase
import com.example.gamecounter.db.repository.GameRepository
import com.example.gamecounter.db.repository.PlayerRepository
import com.example.gamecounter.db.repository.RoundRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class GameCounterApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { GameCounterDatabase.getDatabase(this, applicationScope) }
    val playerRepository by lazy { PlayerRepository(database.playerDao()) }
    val roundRepository by lazy { RoundRepository(database.roundDao()) }
    val gameRepository by lazy { GameRepository(database.gameDao()) }
//    val playerRoundDao by lazy { PlayerRoundRepository(database.playerRoundDao()) }
}