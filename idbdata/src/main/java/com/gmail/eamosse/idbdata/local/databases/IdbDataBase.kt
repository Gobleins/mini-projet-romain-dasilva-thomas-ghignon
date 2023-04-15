package com.gmail.eamosse.idbdata.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.eamosse.idbdata.local.daos.*
import com.gmail.eamosse.idbdata.local.entities.*

/**
 * Modélise la base de données ainsi que les tables de la BDD
 */
@Database(
entities = [ActorEntity::class,
            ActorMovieCrossRef::class,
            ActorSerieCrossRef::class,
            CategoryEntity::class,
            CategoryMovieCrossRef::class,
            EpisodeEntity::class,
            MovieActorCrossRef::class,
            MovieEntity::class,
            SeasonEntity::class,
            SeasonEpisodeCrossRef::class,
            SerieCategoryCrossRef::class,
            SerieEntity::class,
            SerieSeasonCrossRef::class,
            TokenEntity::class],
    version = 18
)
internal abstract class IdbDataBase : RoomDatabase() {
    abstract fun actorDao(): ActorDao
    abstract fun tokenDao(): TokenDao
    abstract fun categoryDao(): CategoryDao
    abstract fun movieDao(): MovieDao
    abstract fun serieDao(): SerieDao

}