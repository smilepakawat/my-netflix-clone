package com.smile.mynetflixclone.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.smile.mynetflixclone.data.SharePreferenceLocalStorage
import com.smile.mynetflixclone.databinding.ActivityLoginBinding
import com.smile.mynetflixclone.movie.MovieActivity
import kotlinx.coroutines.Dispatchers

class LoginActivity : AppCompatActivity(), LoginPresenter.LoginView {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val presenter by lazy {
        LoginPresenter(
            Dispatchers.Main,
            2000,
            SharePreferenceLocalStorage(this)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter.attachView(this)
        presenter.askUsername()

        binding.buttonSubmit.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            presenter.login(username, password)
        }
    }

    override fun goToMovieScreen(user: User) {
        val intent = Intent(this, MovieActivity::class.java)
        intent.putExtra(MovieActivity.EXTRA_USER, user)
        startActivity(intent)
    }

    override fun showToastMessage() {
        Toast.makeText(
            this,
            "I think your username and password is admin",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showProgressBar() {
        binding.progressBar.isVisible = true
    }

    override fun hideProgressBar() {
        binding.progressBar.isVisible = false
    }

    override fun onUsernameSaved(username: String) {
        binding.username.setText(username)
    }
}