package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Episode
import com.gmail.eamosse.idbdata.local.entities.EpisodeEntity
import com.google.gson.annotations.SerializedName

data class EpisodesResponse(
    @SerializedName("episodes")
    val episodes: List<EpisodeResponse>
)

data class EpisodeResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("still_path")
    val still_path: String?,
    @SerializedName("episode_number")
    val episode_number: Int,
    @SerializedName("air_date")
    val air_date: String
){
    internal fun toEpisode() = Episode(
        id = this.id,
        name = this.name,
        overview = this.overview,
        still_path = this.still_path ?: "",
        episode_number = this.episode_number,
        air_date = this.air_date
    )

    internal fun toEntity() = EpisodeEntity(
        id = this.id,
        name = this.name,
        overview = this.overview,
        still_path = this.still_path ?: "",
        episode_number = this.episode_number,
        air_date = this.air_date
    )
}