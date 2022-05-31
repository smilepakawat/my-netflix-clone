package com.smile.mynetflixclone.home_movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smile.mynetflixclone.R
import com.smile.mynetflixclone.data.MovieAPIFactory
import com.smile.mynetflixclone.login.User
import com.smile.mynetflixclone.databinding.FragmentHomeMovieBinding
import com.smile.mynetflixclone.movie.Movie
import com.smile.mynetflixclone.movie.MovieActivity

class HomeMovieFragment : Fragment(), HomeMoviePresenter.HomeMovieView {

    private val binding by lazy { FragmentHomeMovieBinding.inflate(layoutInflater) }
    private val presenter by lazy { HomeMoviePresenter(MovieAPIFactory.createAPI()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.attachView(this)
        presenter.fetchMovie()
    }

    override fun showMovies(movies: List<Movie>) {
        val user = arguments?.getParcelable<User>(MovieActivity.EXTRA_USER)
        val adapter = HomeMovieAdapter(movies) { movie ->
            findNavController().apply {
                navigate(
                    R.id.action_homeMovieFragment_to_movieDetailFragment,
                    bundleOf(
                        MovieActivity.EXTRA_MOVIE to movie,
                        MovieActivity.EXTRA_USER to user
                    )
                )
            }
        }
        val layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.recycleView.layoutManager = layoutManager
        binding.recycleView.adapter = adapter
    }
}