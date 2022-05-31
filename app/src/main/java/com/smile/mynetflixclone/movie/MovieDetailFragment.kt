package com.smile.mynetflixclone.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.smile.mynetflixclone.R
import com.smile.mynetflixclone.login.User
import com.smile.mynetflixclone.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {

    private val binding by lazy { FragmentMovieDetailBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() {
        val user = arguments?.getParcelable<User>(MovieActivity.EXTRA_USER)
        val movie = arguments?.getParcelable<Movie>(MovieActivity.EXTRA_MOVIE)
        with(binding) {
            tvMovieName.text = movie?.name.orEmpty()
            tvDuration.text = getString(R.string.duration).format(movie?.duration)
            helloUser.text = getString(R.string.greeting).format(user?.username.orEmpty())
            tvDescription.text = movie?.description.orEmpty()
            Glide.with(binding.imageView).load(movie?.image).into(binding.imageView)
        }
    }
}