package com.example.gamecounter.viewmodel

import androidx.lifecycle.*
import com.example.gamecounter.db.model.Player
import com.example.gamecounter.db.model.PlayerWithRounds
import com.example.gamecounter.db.repository.PlayerRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class PlayerViewModel(private val playerRepository: PlayerRepository) :
    ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allPlayers: LiveData<List<Player>> = playerRepository.allPlayers.asLiveData()

    fun insert(player: Player) = viewModelScope.launch {
        playerRepository.insert(player)
    }

}

class PlayerViewModelFactory(private val repository: PlayerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}