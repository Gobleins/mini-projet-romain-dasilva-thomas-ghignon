package com.gmail.eamosse.idbdata.local.entities

import androidx.room.Entity

@Entity(
    tableName = "season_episode_cross_ref",
    primaryKeys = ["season_id", "episode_id"],
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = SeasonEntity::class,
            parentColumns = ["season_id"],
            childColumns = ["season_id"]
        ),
        androidx.room.ForeignKey(
            entity = EpisodeEntity::class,
            parentColumns = ["episode_id"],
            childColumns = ["episode_id"]
        )
    ]
)

internal data class SeasonEpisodeCrossRef(
    val season_id: Int,
    val episode_id: Int
)


