package com.smile.mynetflixclone.login

import com.smile.mynetflixclone.data.LocalStorage
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Test

class LoginPresenterTest {

    @Test
    fun `when input username and password correct should call goToMovieScreen`() {
        // Arrange = Given a context, prepare environment or object
        val presenter = LoginPresenter(Dispatchers.Unconfined, 0, StubLocalStorage())
        val view = SpyLoginView()
        presenter.attachView(view)

        // Act = Action, Behavior
        presenter.login("admin", "admin")

        // Assert = Verify the result
        val expectGoToMovie = 1
        val expectShowToastMessage = 0
        assertEquals(expectGoToMovie, view.spyGoToMovieScreen)
        assertEquals(expectShowToastMessage, view.spyShowToastMessage)
    }

    @Test
    fun `when input username and password should call showToastMessage`() {
        val presenter = LoginPresenter(Dispatchers.Unconfined, 0, StubLocalStorage())
        val view = SpyLoginView()
        presenter.attachView(view)

        presenter.login("admin", "1234")

        val expect = 1
        assertEquals(expect, view.spyShowToastMessage)
    }

    @Test
    fun `when call localStorage read should return admin`() {
        val presenter = LoginPresenter(Dispatchers.Unconfined, 0, StubLocalStorage())
        val view = SpyLoginView()
        presenter.attachView(view)

        presenter.askUsername()

        val expect = "admin"
        assertEquals(expect, view.spyUsername)
    }

    class StubLocalStorage: LocalStorage {
        override fun insert(data: String) {

        }

        override fun read(): String {
            return "admin"
        }

    }

    class SpyLoginView: LoginPresenter.LoginView {

        var spyUsername: String = ""
        var spyGoToMovieScreen = 0
        var spyShowToastMessage = 0

        override fun goToMovieScreen(user: User) {
            spyGoToMovieScreen += 1
        }

        override fun showToastMessage() {
            spyShowToastMessage +=1
        }

        override fun showProgressBar() {

        }

        override fun hideProgressBar() {

        }

        override fun onUsernameSaved(username: String) {
            spyUsername = username
        }

    }
}