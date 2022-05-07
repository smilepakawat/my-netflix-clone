package com.smile.mynetflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.smile.mynetflixclone.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonSubmit.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val user = User(username, password)
            binding.progressBar.isVisible = true
            Handler(Looper.getMainLooper()).postDelayed({
                binding.progressBar.isVisible = false
                if (username == "admin" && password == "admin") {
                    Log.d("LoginState", "Authenticated")
                    val intent = Intent(this, MovieActivity::class.java)
                    intent.putExtra(MovieActivity.EXTRA_USER, user)
                    startActivity(intent)
                } else {
                    Log.d("LoginState", "Username and Password is incorrect")
                    Toast.makeText(
                        this,
                        "Fail to Authenticate",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }, 3000)
        }
    }
}