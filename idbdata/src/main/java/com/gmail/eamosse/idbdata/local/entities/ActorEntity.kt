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
    val imdb_id: String,
    val name: String,
)

internal fun ActorEntity.toActor(): Actor {
    return Actor(
        id = this.id,
        imdb_id = this.imdb_id,
        name = this.name,
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
        imdb_id = actor.imdb_id,
        name = actor.name,
        movies = this.movies.map{
            it.toMovie()
        }
    )
}