package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Season
import com.gmail.eamosse.idbdata.local.entities.SeasonEntity
import com.google.gson.annotations.SerializedName

internal data class SeasonsResponse(
    @SerializedName("seasons")
    val seasons: List<SeasonResponse>
)

internal data class SeasonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("season_number")
    val season_number: Int
){
    internal fun toSeason() = Season(
        id = this.id,
        name = this.name,
        overview = this.overview,
        poster_path = this.poster_path ?: "",
        season_number = this.season_number
    )

    internal fun toEntity() = SeasonEntity(
        id = this.id,
        name = this.name,
        overview = this.overview,
        poster_path = this.poster_path ?: "",
        season_number = this.season_number
    )
}