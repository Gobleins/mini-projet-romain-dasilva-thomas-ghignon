package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.utils.Result

interface MovieDataSource {
    suspend fun getActor(id: Int): Result<Actor>
    suspend fun getPopularActors(): Result<List<Actor>>
    suspend fun getCategories(): Result<List<Category>>
    suspend fun getCategory(id: Int): Result<Category>
    suspend fun getMovie(id: Int): Result<Movie>
    suspend fun getMovieActors(id: Int): Result<List<Actor>>
    suspend fun getMoviesByCategory(category: Category, limit: Int = 10): Result<List<Movie>>
    suspend fun getPopularMovies(limit: Int = 10): Result<List<Movie>>
    suspend fun getSerie(id: Int): Result<Serie>
    suspend fun getPopularSeries(limit: Int = 10): Result<List<Serie>>
    suspend fun getSeriesByCategory(category: Category, limit: Int = 10): Result<List<Serie>>

    suspend fun getSeason(tvId: Int, seasonNumber: Int,): Result<Season>
    suspend fun getEpisode(tvId: Int, seasonNumber: Int, episodeNumber: Int): Result<Episode>
    suspend fun getToken(): Result<Token>
    suspend fun saveActor(actor: Actor)
    suspend fun saveCategories(categories: List<Category>)
    suspend fun saveMovie(movie: Movie)
    suspend fun saveMovies(movies: List<Movie>)
    suspend fun saveSerie(serie: Serie)
    suspend fun saveSeries(series: List<Serie>)

    suspend fun saveSeason(season: Season)
    suspend fun saveSeasons(seasons: List<Season>)
    suspend fun saveEpisode(episode: Episode)
    suspend fun saveEpisodes(episodes: List<Episode>)
    suspend fun saveToken(token: Token)


}