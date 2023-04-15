package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Actor
import com.gmail.eamosse.idbdata.local.entities.ActorEntity
import com.google.gson.annotations.SerializedName

data class CreditsResponse(
    @SerializedName("cast")
    val credits: List<CreditResponse>)


data class CreditResponse(
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
{
    internal fun toActor() = Actor(
        id = this.id,
        imdb_id = this.imdb_id ?: "",
        name = this.name,
        popularity = this.popularity,
        profile_path = this.profile_path ?: "",
        biography = this.biography
    )

    internal fun toEntity() = ActorEntity(
        id = this.id,
        imdb_id = this.imdb_id ?: "",
        name = this.name,
        popularity = this.popularity,
        profile_path = this.profile_path ?: "",
        biography = this.biography
    )
}

