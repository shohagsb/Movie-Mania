package me.shohag.moviemania.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.shohag.moviemania.data.network.model.MovieResponse
import me.shohag.moviemania.databinding.ItemMovieBinding

class MovieAdapter(private val listener: (MovieResponse.Result) -> Unit) :
    PagingDataAdapter<MovieResponse.Result, MovieAdapter.ViewHolder>(MODEL_COMPARATOR) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieResponse.Result, listener: (MovieResponse.Result) -> Unit) {
            binding.movie = movie
            binding.root.setOnClickListener {
                listener.invoke(movie)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = ItemMovieBinding.inflate(inflater, parent, false)
                return ViewHolder(view)
            }
        }

    }

    companion object {
        private val MODEL_COMPARATOR = object : DiffUtil.ItemCallback<MovieResponse.Result>() {
            override fun areItemsTheSame(
                oldItem: MovieResponse.Result,
                newItem: MovieResponse.Result
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieResponse.Result,
                newItem: MovieResponse.Result
            ): Boolean =
                oldItem == newItem
        }
    }
}