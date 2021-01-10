package com.example.gamecounter.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.gamecounter.R
import com.example.gamecounter.databinding.ActivityNewPlayerBinding

class NewPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTitle(R.string.new_player_activity_title)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_player)
        val editNameView = binding.editName
        val editAgeView = binding.editAge

        val submitButton = binding.buttonSave
        submitButton.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNameView.text) || TextUtils.isEmpty(editAgeView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val name = editNameView.text.toString()
                val age = editAgeView.text.toString()
                replyIntent.putExtra(EXTRA_NAME, name)
                replyIntent.putExtra(EXTRA_AGE, age.toInt())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }

    companion object {
        const val EXTRA_NAME = "com.example.android.gamecounter.NAME"
        const val EXTRA_AGE = "com.example.android.gamecounter.AGE"
    }
}