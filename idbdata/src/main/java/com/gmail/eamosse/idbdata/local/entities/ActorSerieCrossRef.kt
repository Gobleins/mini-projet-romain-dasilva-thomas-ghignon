package com.gmail.eamosse.idbdata.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "actor_serie_cross_ref",
    primaryKeys = ["serie_id", "actor_id"],
    foreignKeys = [
        ForeignKey(
            entity = SerieEntity::class,
            parentColumns = ["serie_id"],
            childColumns = ["actor_id"]
        ),
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = ["actor_id"],
            childColumns = ["serie_id"]
        )
    ]
)

data class ActorSerieCrossRef(
    val actor_id: Int,
    val serie_id: Int
)
