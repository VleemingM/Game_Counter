package com.example.gamecounter.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gamecounter.R
import com.example.gamecounter.viewmodel.PlayerViewModel

class CatanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var catanViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)

    }
}