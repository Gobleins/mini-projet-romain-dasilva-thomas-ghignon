package com.gmail.eamosse.idbdata.api.response

import com.google.gson.annotations.SerializedName

data class DetailPersonMovieResponse(
    @SerializedName("cast")
    val cast: List<CastResponse>,
    //@Json(name = "crew")
    //val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)
data class CastResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("poster_path")
    val posterPath: String?,
)


internal fun CastResponse.toCast(): Cast {
    return Cast(backdropPath = this.backdropPath, id = this.id, posterPath = this.posterPath)
}
data class Cast(
    val backdropPath : String?,
    val id: Int?,
    val posterPath: String?
)