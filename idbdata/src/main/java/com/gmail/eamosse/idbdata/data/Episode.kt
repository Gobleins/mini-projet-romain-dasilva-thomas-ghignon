package com.gmail.eamosse.idbdata.data

import com.gmail.eamosse.idbdata.local.entities.EpisodeEntity

data class Episode(
    val id: Int,
    val name: String,
    val overview: String,
    val still_path: String?,
    val episode_number: Int,
    val air_date: String
)

internal fun Episode.toEntity() = EpisodeEntity(
    id = this.id,
    name = this.name,
    overview = this.overview,
    still_path = this.still_path ?: "",
    episode_number = this.episode_number,
    air_date = this.air_date
)