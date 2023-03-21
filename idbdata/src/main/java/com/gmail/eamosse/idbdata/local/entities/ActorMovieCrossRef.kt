package com.gmail.eamosse.idbdata.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "actor_movie_cross_ref",
    primaryKeys = ["actor_id", "movie_id"],
    foreignKeys = [
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = ["actor_id"],
            childColumns = ["movie_id"]
        ),
        ForeignKey(
            entity = MovieEntity::class,
            parentColumns = ["movie_id"],
            childColumns = ["actor_id"]
        )
    ]
)
data class ActorMovieCrossRef(
    val actor_id: Int,
    val movie_id: Int
)
