package com.gmail.eamosse.idbdata.local.entities

import androidx.room.*
import com.gmail.eamosse.idbdata.data.Movie


@Entity(
    tableName = "movie"
)
internal data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val id: Int,
    val imdb_id: String,
    val title: String,
    val video: Boolean,
    val popularity: Double,
    val poster_path: String,
    val backdrop_path: String,
    val release_date: String,
    val vote_average: Double,
    val overview: String
)

internal fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = this.id,
        imdb_id = this.imdb_id,
        title = this.title,
        video = this.video,
        popularity = this.popularity,
        poster_path = this.poster_path,
        backdrop_path = this.backdrop_path,
        release_date = this.release_date,
        vote_average = this.vote_average,
        overview = this.overview,
    )
}

internal data class MovieWithActorsAndCategory(
    @Embedded val movie: MovieEntity,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "actor_id",
        associateBy = Junction(ActorMovieCrossRef::class)
    )
    val actors: List<ActorEntity>,
    @Relation(
        parentColumn = "movie_id",
        entityColumn = "category_id",
        associateBy = Junction(CategoryMovieCrossRef::class)
    )
    val categories: List<CategoryEntity>
)


internal fun MovieWithActorsAndCategory.toMovie(): Movie {
    return Movie(
        id = this.movie.id,
        imdb_id = movie.imdb_id,
        title = movie.title,
        video = movie.video,
        popularity = movie.popularity,
        poster_path = movie.poster_path,
        backdrop_path = movie.backdrop_path,
        category = this.categories.map{
            it.toCategory()
        },
        release_date = movie.release_date,
        vote_average = movie.vote_average,
        overview = movie.overview,
        actors = this.actors.map {
            it.toActor()
        }
    )
}
