package com.gmail.eamosse.idbdata.local.entities

import androidx.room.*
import com.gmail.eamosse.idbdata.data.Actor

@Entity(
    tableName = "actor"
)

internal data class ActorEntity(
    @PrimaryKey
    @ColumnInfo(name = "actor_id")
    val id: Int,
    val imdb_id: String?,
    val name: String,
    val profile_path: String?
)

internal fun ActorEntity.toActor(): Actor {
    return Actor(
        id = this.id,
        imdb_id = this.imdb_id ?: "",
        name = this.name,
        profile_path = this.profile_path ?: ""
    )
}

internal data class ActorWithMovies(
    @Embedded val actor: ActorEntity,
    @Relation(
        parentColumn = "actor_id",
        entityColumn = "movie_id",
        associateBy = Junction(ActorMovieCrossRef::class)
    )
    val movies: List<MovieEntity>
)

internal fun ActorWithMovies.toActor(): Actor {
    return Actor(
        id = actor.id,
        imdb_id = actor.imdb_id ?: "",
        name = actor.name,
        profile_path = actor.profile_path ?: "",
        movies = this.movies.map{
            it.toMovie()
        }
    )
}