package me.shohag.moviemania.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.shohag.moviemania.data.network.RemoteDataSource
import me.shohag.moviemania.data.network.service.MovieApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BMS_BASE_URL = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun provideMovieApiService(
        remoteDataSource: RemoteDataSource
    ): MovieApiService {
        return remoteDataSource.buildApi(BMS_BASE_URL, MovieApiService::class.java)
    }
}