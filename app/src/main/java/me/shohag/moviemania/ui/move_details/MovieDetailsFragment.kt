package me.shohag.moviemania.ui.move_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import me.shohag.moviemania.databinding.FragmentMovieDetailsBinding


@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private lateinit var _binding: FragmentMovieDetailsBinding

    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        if (!::_binding.isInitialized) {
            _binding = FragmentMovieDetailsBinding.inflate(layoutInflater)
        }
        _binding.movie = args.movie
        _binding.lifecycleOwner = this


        return _binding.root
    }

}