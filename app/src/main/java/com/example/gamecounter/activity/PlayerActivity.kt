package com.example.gamecounter.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamecounter.GameCounterApplication
import com.example.gamecounter.R
import com.example.gamecounter.adapter.PlayerListAdapter
import com.example.gamecounter.databinding.ActivityMainBinding
import com.example.gamecounter.db.model.Player
import com.example.gamecounter.viewmodel.PlayerViewModel
import com.example.gamecounter.viewmodel.PlayerViewModelFactory

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val newPlayerActivityRequestCode = 1
    private val playerViewModel: PlayerViewModel by viewModels {
        PlayerViewModelFactory((application as GameCounterApplication).playerRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.player_activity_title)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        val recyclerView = binding.recyclerview
        val adapter = PlayerListAdapter()
        recyclerView.adapter = adapter

        playerViewModel.allPlayers.observe(this, Observer { players ->
            //Update the cached copy of the words in the adapter.
            players?.let { adapter.submitList(it) }
        })

        binding.fab.setOnClickListener {
            val intent = Intent(this@PlayerActivity, SingleGameRoundActivity::class.java)
            startActivityForResult(intent, newPlayerActivityRequestCode)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newPlayerActivityRequestCode && resultCode == RESULT_OK) {
            data?.getStringExtra(NewPlayerActivity.EXTRA_NAME)?.let { name ->
                data.getIntExtra(NewPlayerActivity.EXTRA_AGE,-1).let { age ->
                    val player = Player(name = name, age = age)
                    playerViewModel.insert(player)
                }
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            )
                .show()

        }
    }


}