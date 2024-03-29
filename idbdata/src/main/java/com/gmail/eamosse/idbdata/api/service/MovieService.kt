package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.TokenResponse
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
    suspend fun getActor(@Path("person_id") actorId: Int): Response<CreditResponse>

    @GET("person/popular")
    suspend fun getPopularActors(): Response<ActorsResponse>

    @GET("person/{person_id}/movie_credits")
    suspend fun getActorMovies(@Path("person_id") actorId: Int): Response<DetailPersonMovieResponse>

//    @GET("movie/{id}/credits")
//    suspend fun getMovieDetailActors(@Path(value = "id") id: Int): Response<CreditsResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: Int): Response<MovieResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieActors(@Path("movie_id") movieId: Int): Response<CreditsResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MoviesResponse>

    @GET("discover/movie")
    suspend fun getMoviesByCategory(@Query("with_genres") category: Int): Response<MoviesResponse>

    @GET("tv/popular")
    suspend fun getPopularSeries(): Response<SeriesResponse>

    @GET("discover/tv")
    suspend fun getSeriesByCategory(@Query("with_genres") category: Int): Response<SeriesResponse>

    @GET("tv/{tv_id}")
    suspend fun getSerie(serieId: Int): Response<SerieResponse>

    @GET("tv/{tv_id}/season/{season_number}/episode/{episode_number}")
    suspend fun getEpisode(
        @Path("tv_id") serieId: Int,
        @Path("season_number") seasonNumber: Int,
        @Path("episode_number") episodeNumber: Int
    ): Response<EpisodeResponse>

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getSeason(@Path("tv_id") serieId: Int, @Path("season_number") seasonNumber: Int): Response<SeasonResponse>

    @GET("/tv/{serieId}/credits")
    suspend fun getSerieActors(serieId: Int): Response<CreditsTvResponse>

}