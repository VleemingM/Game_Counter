package com.example.gamecounter.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.gamecounter.GameCounterApplication
import com.example.gamecounter.db.model.Player
import com.example.gamecounter.db.model.Round
import kotlinx.coroutines.launch

class SingleGameRoundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val game =
                (application as GameCounterApplication).gameRepository.allGames.asLiveData()
            val player: Player? = (application as GameCounterApplication).playerRepository.first
            val rounds = listOf(
                game.value?.get(0)
                    ?.let { Round(gameId = it.gameId, winningPlayerId = player?.playerId) }
            )
            rounds.forEach { round ->
                (application as GameCounterApplication).roundRepository.insert(
                    round!!
                )
            }
        }

    }
}