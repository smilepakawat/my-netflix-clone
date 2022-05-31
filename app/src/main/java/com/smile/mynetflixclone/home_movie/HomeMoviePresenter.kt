package com.smile.mynetflixclone.home_movie

import com.smile.mynetflixclone.data.MovieAPI
import com.smile.mynetflixclone.movie.Movie
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HomeMoviePresenter constructor(
    private val api: MovieAPI
) {

    private val scope = MainScope()
    private lateinit var view: HomeMovieView

    fun attachView(view: HomeMovieView) {
        this.view = view
    }

    interface HomeMovieView {
        fun showMovies(movies: List<Movie>)
    }

    fun fetchMovie() {
        scope.launch {
            val movies = api.getMovie()
                .map { Movie(
                        it.id,
                        it.name,
                        it.image,
                        it.duration,
                        it.description) }
            view.showMovies(movies)
        }
    }
}