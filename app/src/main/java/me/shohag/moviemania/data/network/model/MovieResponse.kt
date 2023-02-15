package me.shohag.moviemania.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    val page: Int,
    val results: List<Result>,
    @field:SerializedName("total_pages")
    val totalPages: Int,
    @field:SerializedName("total_results")
    val totalResults: Int,
):Parcelable {
    @Parcelize
    data class Result(
        val id: Long,
        val adult: Boolean,
        @field:SerializedName("backdrop_path")
        val backdropPath: String,
        @field:SerializedName("original_language")
        val originalLanguage: String,
        @field:SerializedName("original_title")
        val originalTitle: String,
        val overview: String,
        val popularity: Double,
        @field:SerializedName("poster_path")
        val posterPath: String,
        @field:SerializedName("release_date")
        val releaseDate: String,
        val title: String,
        val video: Boolean,
        @field:SerializedName("vote_average")
        val voteAverage: Double,
        @field:SerializedName("vote_count")
        val voteCount: Int,
    ):Parcelable
}