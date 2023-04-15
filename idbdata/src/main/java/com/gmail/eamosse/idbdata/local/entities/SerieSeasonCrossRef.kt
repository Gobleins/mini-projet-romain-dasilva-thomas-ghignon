package com.gmail.eamosse.idbdata.local.entities

import androidx.room.Entity

@Entity(
    tableName = "serie_season_cross_ref",
    primaryKeys = ["serie_id", "season_id"],
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = SerieEntity::class,
            parentColumns = ["serie_id"],
            childColumns = ["serie_id"]
        ),
        androidx.room.ForeignKey(
            entity = SeasonEntity::class,
            parentColumns = ["season_id"],
            childColumns = ["season_id"]
        )
    ]
)

data class SerieSeasonCrossRef(
    val serie_id: Int,
    val season_id: Int
)
