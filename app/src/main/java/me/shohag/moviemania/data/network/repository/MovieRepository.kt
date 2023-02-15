package me.shohag.moviemania.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.shohag.moviemania.BuildConfig
import me.shohag.moviemania.adapter.MoviePagingSource
import me.shohag.moviemania.data.network.model.MovieResponse
import me.shohag.moviemania.data.network.service.MovieApiService
import javax.inject.Inject


class MovieRepository @Inject constructor(private val service: MovieApiService) {

    /**
     * Fetch Top Movies
     *
     * */
    fun getTopMovies(): Flow<PagingData<MovieResponse.Result>> {
        return Pager(config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false
        ), pagingSourceFactory = {
            MoviePagingSource(
                service = service, apiKey = BuildConfig.API_TOKEN
            )
        }).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}