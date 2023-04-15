package com.gmail.eamosse.idbdata.local.entities

import androidx.room.*
import com.gmail.eamosse.idbdata.data.*

@Entity(
    tableName = "serie"
)

internal data class SerieEntity(
    @PrimaryKey
    @ColumnInfo(name = "serie_id")
    val id: Int,
    val title: String,
    val video: Boolean,
    val popularity: Double,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Double,
    val overview: String
)

internal fun SerieEntity.toSerie(): Serie{
    return Serie(
        id = this.id,
        name = this.title,
        video = this.video,
        popularity = this.popularity,
        poster_path = this.poster_path ?: "",
        backdrop_path = this.backdrop_path ?: "",
        vote_average = this.vote_average,
        overview = this.overview
    )
}

internal data class SerieWithSeasons(
    @Embedded val serie: SerieEntity,
    @Relation(
        parentColumn = "serie_id",
        entityColumn = "season_id",
        associateBy = Junction(SerieSeasonCrossRef::class)
    )
    val seasons: List<SeasonEntity>
)

internal fun SerieWithSeasons.toSerie(): Serie{
    return Serie(
        id = serie.id,
        name = serie.title,
        video = serie.video,
        popularity = serie.popularity,
        poster_path = serie.poster_path ?: "",
        backdrop_path = serie.backdrop_path ?: "",
        vote_average = serie.vote_average,
        overview = serie.overview,
        seasons = this.seasons.map{
            it.toSeason()
        }
    )
}