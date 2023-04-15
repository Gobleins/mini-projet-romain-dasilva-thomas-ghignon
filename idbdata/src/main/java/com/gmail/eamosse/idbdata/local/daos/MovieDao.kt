package com.gmail.eamosse.idbdata.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.local.entities.*
import com.gmail.eamosse.idbdata.local.entities.ActorEntity
import com.gmail.eamosse.idbdata.local.entities.MovieEntity

@Dao
internal interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM movie WHERE movie_id = :id")
    fun getMovie(id: Int): MovieWithActorsAndCategory?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveActors(actors: List<ActorEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveActorMovieCrossRef(actorMovieCrossRef: ActorMovieCrossRef)

    @Query("SELECT * FROM movie INNER JOIN category_movie_cross_ref ON movie.movie_id = category_movie_cross_ref.movie_id WHERE category_movie_cross_ref.category_id = :category_id ORDER BY popularity DESC LIMIT :limit")
    fun getMoviesByCategory(category_id: Int, limit: Int = 10): List<MovieEntity>

    @Query("SELECT * FROM movie ORDER BY popularity DESC LIMIT :limit")
    fun getPopularMovies(limit: Int = 10): List<MovieEntity>

    @Query("SELECT * FROM actor INNER JOIN movie_actor_cross_ref ON actor.actor_id = movie_actor_cross_ref.actor_id WHERE movie_actor_cross_ref.movie_id = :movie_id")
    fun getMovieActors(movie_id: Int): List<ActorEntity>


}