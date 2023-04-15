package com.gmail.eamosse.idbdata.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "movie_actor_cross_ref",
    primaryKeys = ["movie_id", "actor_id"],
    foreignKeys = [
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["movie_id"],
            childColumns = ["movie_id"]
        ),
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = ["actor_id"],
            childColumns = ["actor_id"]
        )
    ]
)

data class MovieActorCrossRef(
    val movie_id: Int,
    val actor_id: Int
)
