package me.shohag.moviemania.data.network.service

import me.shohag.moviemania.data.network.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("movie/top_rated")
    suspend fun fetchTopMovies(
        @Query("api_key") apiKey : String,
        @Query("page") page : Int,
    ): MovieResponse

}