package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.data.Category
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("genre/movie/list?language=fr-FR")
    suspend fun getCategories(): Response<CategoriesResponse>

    @GET("person/{person_id}")
    suspend fun getActor(@Path("person_id") actorId: Int): Response<ActorResponse>

    @GET("person/{person_id}/movie_credits")
    suspend fun getActorMovies(@Path("person_id") actorId: Int): Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: Int): Response<MovieResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieActors(@Path("movie_id") movieId: Int): Response<ActorsResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MoviesResponse>

    @GET("discover/movie")
    suspend fun getMoviesByCategory(@Query("with_genres") category: Int): Response<MoviesResponse>


}