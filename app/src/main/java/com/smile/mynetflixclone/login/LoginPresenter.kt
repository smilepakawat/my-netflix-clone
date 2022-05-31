package com.smile.mynetflixclone.login

import com.smile.mynetflixclone.data.LocalStorage
import com.smile.mynetflixclone.data.SharePreferenceLocalStorage
import kotlinx.coroutines.*

class LoginPresenter constructor(
    dispatcher: CoroutineDispatcher,
    private val duration: Long,
    private val localStorage: LocalStorage
) {

    private lateinit var view: LoginView
    private val scope = CoroutineScope(Job() + dispatcher)

    fun attachView(view: LoginView) {
        this.view = view
    }

    fun askUsername() {
        val username = localStorage.read()
        view.onUsernameSaved(username)
    }

    interface LoginView {
        fun goToMovieScreen(user: User)
        fun showToastMessage()
        fun showProgressBar()
        fun hideProgressBar()
        fun onUsernameSaved(username: String)
    }

    fun login(username: String, password: String) {
        scope.launch {
            view.showProgressBar()
            delay(duration)

            if (username == "admin" && password == "admin") {
                val user = User(username, password)
                localStorage.insert(username)
                view.goToMovieScreen(user)
            } else {
                view.showToastMessage()
            }

            view.hideProgressBar()
        }
    }
}