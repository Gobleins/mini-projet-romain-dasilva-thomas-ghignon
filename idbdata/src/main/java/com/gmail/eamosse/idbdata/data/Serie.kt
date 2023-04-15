package com.gmail.eamosse.idbdata.data

import com.gmail.eamosse.idbdata.local.entities.SerieEntity
import com.gmail.eamosse.idbdata.utils.ItemList


data class Serie(
    val id: Int,
    val name: String,
    val video: Boolean,
    val popularity: Double,
    val poster_path: String?,
    val backdrop_path: String?,
    var category: List<Category> = listOf(),
    val vote_average: Double,
    val overview: String,
    var actors: List<Actor> = listOf(),
    var seasons: List<Season> = listOf(),
    var networks: List<Network> = listOf()
)

internal fun Serie.toEntity() = SerieEntity(
    id = this.id,
    title = this.name,
    video = this.video,
    popularity = this.popularity,
    poster_path = this.poster_path ?: "",
    backdrop_path = this.backdrop_path ?: "",
    vote_average = this.vote_average,
    overview = this.overview
)