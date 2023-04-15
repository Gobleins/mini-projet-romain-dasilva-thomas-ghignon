package com.gmail.eamosse.idbdata.data

import com.gmail.eamosse.idbdata.local.entities.SeasonEntity

data class Season(
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val season_number: Int,
    val episodes: List<Episode> = listOf()
)

internal fun Season.toEntity() = SeasonEntity(
    id = this.id,
    name = this.name,
    overview = this.overview,
    poster_path = this.poster_path ?: "",
    season_number = this.season_number
)