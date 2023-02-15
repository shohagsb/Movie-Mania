package me.shohag.moviemania.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.shohag.moviemania.adapter.MoviePagingSource
import me.shohag.moviemania.data.network.model.MovieResponse
import me.shohag.moviemania.data.network.service.MovieApiService
import javax.inject.Inject


class MovieRepository @Inject constructor(private val service: MovieApiService) {

    fun getTopMovies(apiKey: String, language: String): Flow<PagingData<MovieResponse.Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(service = service, apiKey = apiKey, language = language) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }

    /**
     * Fetch all top movies
     * @param
     * apiKey, language, page
     *
     * @return
     * movieModel
     * */
//    fun fetchTopMovies(
//        apiKey: String, language: String, page: Int,
//    ): Flow<MovieResponse> = flow {
//        val movies = service.fetchTopMovies(
//            apiKey = apiKey, language = language, page = page
//        )
//        emit(movies)
//    }.flowOn(Dispatchers.IO)
}