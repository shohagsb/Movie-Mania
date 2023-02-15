package me.shohag.moviemania.ui.top_movies


import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import me.shohag.moviemania.data.network.model.MovieResponse
import me.shohag.moviemania.data.network.repository.MovieRepository
import javax.inject.Inject

@HiltViewModel
class TopMoviesVM @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    fun fetchTopMovies(apiKey: String, language: String) : Flow<PagingData<MovieResponse.Result>> =
        repository.getTopMovies(apiKey = apiKey, language = language)

}
