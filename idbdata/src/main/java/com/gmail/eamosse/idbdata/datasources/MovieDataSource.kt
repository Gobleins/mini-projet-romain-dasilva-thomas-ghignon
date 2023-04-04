package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.data.Actor
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.Token
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
    suspend fun getToken(): Result<Token>
    suspend fun saveActor(actor: Actor)
    suspend fun saveCategories(categories: List<Category>)
    suspend fun saveMovie(movie: Movie)
    suspend fun saveMovies(movies: List<Movie>)
    suspend fun saveToken(token: Token)

}