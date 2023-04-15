package com.gmail.eamosse.idbdata.data

import com.gmail.eamosse.idbdata.local.entities.ActorEntity
import com.gmail.eamosse.idbdata.utils.ItemList

data class Actor(
    val id: Int,
    val imdb_id: String?,
    override val name: String,
    val popularity: Double?,
    val profile_path: String?,
    var movies: List<Movie> = listOf(),
): ItemList {
    override val identifier: Int
        get() = id
    override val image: String
        get() = profile_path ?: ""
}




internal fun Actor.toEntity() = ActorEntity(
    id = this.id,
    imdb_id = this.imdb_id ?: "",
    name = this.name,
    popularity = this.popularity ?: 0.0,
    profile_path = this.profile_path ?: ""
)
