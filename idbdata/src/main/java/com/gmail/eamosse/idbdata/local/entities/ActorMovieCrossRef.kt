package com.gmail.eamosse.idbdata.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["actor_id", "movie_id"])
data class ActorMovieCrossRef(
    val actorId: Int,
    val movieId: Int
)