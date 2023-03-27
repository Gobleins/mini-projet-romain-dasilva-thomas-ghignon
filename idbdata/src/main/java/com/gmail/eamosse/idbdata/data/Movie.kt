package com.gmail.eamosse.idbdata.data

import com.gmail.eamosse.idbdata.local.entities.MovieEntity


data class Movie(
    val id: Int,
    val imdb: String?,
    val title: String,
    val video: Boolean,
    val popularity: Double,
    val poster_path: String?,
    val backdrop_path: String?,
    var category: List<Category> = listOf(),
    val release_date: String,
    val vote_average: Double,
    val overview: String,
    var actors: List<Actor> = listOf()
)

internal fun Movie.toEntity() = MovieEntity(
    id = this.id,
    imdb = this.imdb ?: "",
    title = this.title,
    video = this.video,
    popularity = this.popularity,
    poster_path = this.poster_path ?: "",
    backdrop_path = this.backdrop_path ?: "",
    release_date = this.release_date,
    vote_average = this.vote_average,
    overview = this.overview
)