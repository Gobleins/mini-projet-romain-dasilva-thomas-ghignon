package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Actor
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.local.entities.MovieEntity
import com.google.gson.annotations.SerializedName


data class MoviesResponse(
    @SerializedName("genres")
    val movies: List<MovieResponse>)

data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdb_id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("overview")
    val overview: String,
){
    internal fun toEntity() = MovieEntity(
        id = this.id,
        imdb_id = this.imdb_id,
        title = this.title,
        video = this.video,
        popularity = this.popularity,
        poster_path = this.poster_path,
        backdrop_path = this.backdrop_path,
        release_date = this.release_date,
        vote_average = this.vote_average,
        overview = this.overview
    )

    internal fun toMovie() = Movie(
        id = this.id,
        imdb_id = this.imdb_id,
        title = this.title,
        video = this.video,
        popularity = this.popularity,
        poster_path = this.poster_path,
        backdrop_path = this.backdrop_path,
        release_date = this.release_date,
        vote_average = this.vote_average,
        overview = this.overview
    )
}

