package com.gmail.eamosse.idbdata.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.eamosse.idbdata.local.entities.ActorEntity
import com.gmail.eamosse.idbdata.local.entities.ActorWithMovies
import com.gmail.eamosse.idbdata.local.entities.MovieEntity

@Dao
internal interface ActorDao {
    @Query("SELECT * FROM actor")
    fun getAll(): List<ActorEntity>

    @Query("SELECT * FROM actor WHERE actor_id = :id")
    fun getActor(id: Int): ActorWithMovies?

    @Query("SELECT * FROM actor ORDER BY popularity DESC LIMIT :limit")
    fun getPopularActors(limit: Int = 10): List<ActorEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveActor(actor: ActorEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveActors(actor: List<ActorEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovies(movies: List<MovieEntity>)

}