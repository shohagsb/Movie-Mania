package me.shohag.moviemania.ui.top_movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.shohag.moviemania.BuildConfig
import me.shohag.moviemania.databinding.FragmentTopMoviesBinding
import me.shohag.moviemania.ui.adapter.MovieAdapter


@AndroidEntryPoint
class TopMoviesFragment : Fragment() {
    private lateinit var _binding: FragmentTopMoviesBinding
    private val viewModel: TopMoviesVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        if (!::_binding.isInitialized) {
            _binding = FragmentTopMoviesBinding.inflate(layoutInflater)
        }
        _binding.rvMovies.layoutManager = GridLayoutManager(requireContext(), 2)
        _binding.rvMovies.adapter = adapter

        bindList()

        return _binding.root
    }

    // Initialize RecyclerView Adapter
    private val adapter by lazy {
        MovieAdapter {
            findNavController().navigate(
                TopMoviesFragmentDirections.actionTopMoviesFragmentToMovieDetailsFragment(it)
            )
        }
    }

    /**
     * Bind Movies to RecyclerView
     * */
    private fun bindList() {
        lifecycleScope.launch {
            viewModel.fetchTopMovies(
                apiKey = BuildConfig.API_TOKEN,
                language = ENG_LANG,
            ).collectLatest(adapter::submitData)
        }
    }

    companion object {
        private const val ENG_LANG = "en-US"
    }

}