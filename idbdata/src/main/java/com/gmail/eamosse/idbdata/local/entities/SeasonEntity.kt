package com.gmail.eamosse.idbdata.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.eamosse.idbdata.data.Season

@Entity(tableName = "season")


internal data class SeasonEntity(
    @PrimaryKey
    @ColumnInfo(name = "season_id")
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int
)

internal fun SeasonEntity.toSeason(): Season{
    return Season(
        id = this.id,
        name = this.name,
        overview = this.overview,
        poster_path = this.poster_path,
        season_number = this.season_number
    )
}

internal fun Season.toEntity() = SeasonEntity(
    id = this.id,
    name = this.name,
    overview = this.overview,
    poster_path = this.poster_path ?: "",
    season_number = this.season_number
)
