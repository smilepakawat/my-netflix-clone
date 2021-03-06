package com.smile.mynetflixclone.home_movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.smile.mynetflixclone.R
import com.smile.mynetflixclone.databinding.MovieItemRowBinding
import com.smile.mynetflixclone.movie.Movie

class HomeMovieAdapter(
    private val movies: List<Movie>,
    private val onclickMovie: (Movie) -> Unit
) :
    RecyclerView.Adapter<HomeMovieAdapter.HomeMovieViewHolder>() {

    class HomeMovieViewHolder(
        private val binding: MovieItemRowBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, onclickMovie: (Movie) -> Unit) {
            Glide.with(binding.imageMovie).load(movie.image)
                .load(movie.image)
                .error(R.drawable.endgame)
                .transition(withCrossFade())
                .into(binding.imageMovie)
            binding.tvMovieName.text = movie.name
            binding.root.setOnClickListener { onclickMovie(movie) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMovieViewHolder {
        val view = MovieItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return HomeMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) {
        holder.bind(movies[position], onclickMovie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}