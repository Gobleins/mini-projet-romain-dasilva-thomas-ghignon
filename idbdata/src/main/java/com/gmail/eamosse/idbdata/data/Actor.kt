package com.gmail.eamosse.idbdata.data

import com.gmail.eamosse.idbdata.local.entities.ActorEntity

data class Actor(
    val id: Int,
    val imdb_id: String,
    val name: String,
    var movies: List<Movie> = listOf()
)

internal fun Actor.toEntity() = ActorEntity(
    id = this.id,
    imdb_id = this.imdb_id,
    name = this.name
)
