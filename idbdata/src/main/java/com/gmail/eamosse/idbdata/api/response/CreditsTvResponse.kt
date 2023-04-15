package com.gmail.eamosse.idbdata.api.response

import com.google.gson.annotations.SerializedName

internal data class CreditsTvResponse(
    @SerializedName("cast")
    val credits: List<CreditResponse>)

internal data class CreditTvResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("imdb_id")
    val imdb_id: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("profile_path")
    val profile_path: String?,
    @SerializedName("biography")
    val biography: String
)
