package com.smile.mynetflixclone

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smile.mynetflixclone.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMovieBinding.inflate(layoutInflater) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER)
        val username = user!!.username
        binding.helloUser.text = "Hey! $username"
    }

    companion object {
        const val EXTRA_USER = "EXTRA_USER"
    }
}