package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Season
import com.gmail.eamosse.idbdata.data.Serie
import com.gmail.eamosse.idbdata.local.entities.SerieEntity
import com.google.gson.annotations.SerializedName

data class SeriesResponse(
    @SerializedName("results")
    val series: List<SerieResponse>
)

data class SerieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("overview")
    val overview: String
    )
{

    internal fun toSerie() = Serie(
        id = this.id,
        name = this.name,
        video = this.video,
        popularity = this.popularity,
        poster_path = this.poster_path ?: "",
        backdrop_path = this.backdrop_path ?: "",
        vote_average = this.vote_average,
        overview = this.overview
    )

    internal fun toEntity() = SerieEntity(
        id = this.id,
        title = this.name,
        video = this.video,
        popularity = this.popularity,
        poster_path = this.poster_path ?: "",
        backdrop_path = this.backdrop_path ?: "",
        vote_average = this.vote_average,
        overview = this.overview
    )
}