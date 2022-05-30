package com.smile.mynetflixclone.home_movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.smile.mynetflixclone.R
import com.smile.mynetflixclone.User
import com.smile.mynetflixclone.databinding.FragmentHomeMovieBinding
import com.smile.mynetflixclone.movie.Movie
import com.smile.mynetflixclone.movie.MovieActivity
import com.smile.mynetflixclone.movie.MovieDetailFragment

class HomeMovieFragment : Fragment() {

    private val binding by lazy { FragmentHomeMovieBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movies = createMovie()
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

    private fun createMovie() = listOf(
        Movie(1, "Avenger Endgame", R.drawable.endgame, 120),
        Movie(2, "Dr.Strange", R.drawable.strange, 130),
        Movie(3, "Iron Man", R.drawable.tony, 125),
        Movie(4, "Thor", R.drawable.thor, 120)
    )

    companion object {
        @JvmStatic
        fun newInstance(user: User?): HomeMovieFragment {
            val fragment = HomeMovieFragment()
            val bundle = Bundle()
            bundle.putParcelable(MovieActivity.EXTRA_USER, user)
            fragment.arguments = bundle
            return fragment
        }
    }
}