package com.gmail.eamosse.idbdata.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.eamosse.idbdata.data.Season
import com.gmail.eamosse.idbdata.local.entities.*
import com.gmail.eamosse.idbdata.local.entities.SeasonEntity
import com.gmail.eamosse.idbdata.local.entities.SerieEntity
import com.gmail.eamosse.idbdata.local.entities.SerieWithSeasons

@Dao
internal interface SerieDao {
    @Query("SELECT * FROM serie")
    fun getAll(): List<SerieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveSerie(serie: SerieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveSeries(series: List<SerieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveSeasonsSeriesCrossRef(actorSerieCrossRef: ActorSerieCrossRef)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveSeason(season: SeasonEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveSeasons(seasons: List<SeasonEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveEpisodes(episodes: List<EpisodeEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveEpisode(episode: EpisodeEntity)

    @Query("SELECT * FROM serie INNER JOIN serie_category_cross_ref ON serie.serie_id = serie_category_cross_ref.serie_id WHERE serie_category_cross_ref.category_id = :category_id ORDER BY popularity DESC LIMIT :limit")
    fun getSeriesByCategory(category_id: Int, limit: Int = 10): List<SerieEntity>

    @Query("SELECT * FROM serie ORDER BY popularity DESC LIMIT :limit")
    fun getPopularSeries(limit: Int = 10): List<SerieEntity>

    @Query("SELECT * FROM serie WHERE serie_id = :id")
    fun getSerie(id: Int): SerieWithSeasons?

    @Query("SELECT * FROM season INNER JOIN serie_season_cross_ref ON season.season_id = serie_season_cross_ref.season_id WHERE serie_season_cross_ref.serie_id = :serie_id AND season.season_number = :season_number")
    fun getSeason(serie_id: Int, season_number: Int): SeasonEntity

    // il y a 2 jointures à faire pour récupérer les épisodes d'une saison et il faut utiliser les 3 paramètres : serie_id: Int, season_number: Int, episode_number: Int
    @Query("SELECT * FROM episode " +
            "INNER JOIN season_episode_cross_ref ON episode.episode_id = season_episode_cross_ref.episode_id " +
            "INNER JOIN season ON season_episode_cross_ref.season_id = season.season_id " +
            "INNER JOIN serie_season_cross_ref ON season.season_id = serie_season_cross_ref.season_id " +
            "INNER JOIN serie ON serie_season_cross_ref.serie_id = serie.serie_id " +
            "WHERE serie.serie_id = :serie_id AND season.season_number = :season_number AND episode.episode_number = :episode_number")
    fun getEpisode(serie_id: Int, season_number: Int, episode_number: Int): EpisodeEntity


}