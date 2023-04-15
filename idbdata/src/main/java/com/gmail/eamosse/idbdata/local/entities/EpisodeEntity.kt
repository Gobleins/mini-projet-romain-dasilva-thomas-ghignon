package com.gmail.eamosse.idbdata.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.eamosse.idbdata.data.Episode

@Entity(
    tableName = "episode"
)

internal data class EpisodeEntity(
    @PrimaryKey
    @ColumnInfo(name = "episode_id")
    val id: Int,
    val name: String,
    val overview: String,
    val still_path: String,
    val episode_number: Int,
    val air_date: String
)

internal fun EpisodeEntity.toEpisode(): Episode {
    return Episode(
        id = this.id,
        name = this.name,
        overview = this.overview,
        still_path = this.still_path,
        episode_number = this.episode_number,
        air_date = this.air_date
    )
}

internal fun Episode.toEntity() = EpisodeEntity(
    id = this.id,
    name = this.name,
    overview = this.overview,
    still_path = this.still_path ?: "",
    episode_number = this.episode_number,
    air_date = this.air_date
)
