package com.smile.mynetflixclone.movie

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.smile.mynetflixclone.R
import com.smile.mynetflixclone.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMovieBinding.inflate(layoutInflater) }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val nav = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = nav.navController
        navController.setGraph(R.navigation.movie_navigation, intent.extras)
    }

    companion object {
        const val EXTRA_USER = "EXTRA_USER"
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }
}