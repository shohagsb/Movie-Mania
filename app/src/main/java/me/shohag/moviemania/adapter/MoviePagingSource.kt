package me.shohag.moviemania.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.shohag.moviemania.data.network.model.MovieResponse
import me.shohag.moviemania.data.network.repository.MovieRepository.Companion.NETWORK_PAGE_SIZE
import me.shohag.moviemania.data.network.service.MovieApiService
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_INDEX = 1

class MoviePagingSource(
    private val service: MovieApiService,
    private val apiKey: String,
) : PagingSource<Int, MovieResponse.Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResponse.Result> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.fetchTopMovies(apiKey = apiKey, page = position)
            val repos = response.results
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, MovieResponse.Result>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}