package com.gmail.eamosse.idbdata.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.eamosse.idbdata.local.daos.CategoryDao
import com.gmail.eamosse.idbdata.local.daos.TokenDao
import com.gmail.eamosse.idbdata.local.entities.ActorMovieCrossRef
import com.gmail.eamosse.idbdata.local.entities.CategoryEntity
import com.gmail.eamosse.idbdata.local.entities.MovieEntity
import com.gmail.eamosse.idbdata.local.entities.TokenEntity

/**
 * Modélise la base de données ainsi que les tables de la BDD
 */
@Database(
    entities = [TokenEntity::class, CategoryEntity::class, MovieEntity::class, ActorMovieCrossRef::class, MovieEntity::class],
    version = 3
)
internal abstract class IdbDataBase : RoomDatabase() {
    abstract fun tokenDao(): TokenDao
    abstract fun categoryDao(): CategoryDao
}