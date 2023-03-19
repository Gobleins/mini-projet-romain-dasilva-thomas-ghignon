package com.gmail.eamosse.idbdata.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.local.entities.ActorEntity
import com.gmail.eamosse.idbdata.local.entities.ActorMovieCrossRef
import com.gmail.eamosse.idbdata.local.entities.MovieEntity
import com.gmail.eamosse.idbdata.local.entities.MovieWithActors

@Dao
internal interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovie(id: Int): MovieWithActors?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveActors(actors: List<ActorEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveActorMovieCrassRef(actorMovieCrossRef: ActorMovieCrossRef)

    @Query("SELECT * FROM movie WHERE category = :category ORDER BY popularity DESC LIMIT :limit")
    fun getMoviesByCategory(category: Category, limit: Int = 10): List<MovieEntity>

    @Query("SELECT * FROM movie ORDER BY popularity DESC LIMIT :limit")
    fun getPopularMovies(limit: Int = 10): List<MovieEntity>

}