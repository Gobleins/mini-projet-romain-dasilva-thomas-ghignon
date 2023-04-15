package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.api.parse
import com.gmail.eamosse.idbdata.api.response.toToken
import com.gmail.eamosse.idbdata.api.safeCall
import com.gmail.eamosse.idbdata.api.service.MovieService
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.utils.Result
import javax.inject.Inject

/**
 * Manipule les données de l'application en utilisant un web service
 * Cette classe est interne au module, ne peut être initialisé ou exposé aux autres composants
 * de l'application
 */
internal class OnlineDataSource @Inject constructor(private val service: MovieService) :
    MovieDataSource {
    override suspend fun getActor(id: Int): Result<Actor> {
        return safeCall {
            val response = service.getActor(id)
            val moviesParse = service.getActorMovies(id).parse()

            when (val responseParse = response.parse()) {
                is Result.Succes -> safeCall {
                    when (moviesParse) {
                        is Result.Succes -> safeCall {
                            val movies = moviesParse.data.movies.map {
                                it.toMovie()
                            }

                            val actor = responseParse.data.toActor()
                            actor.movies = movies

                            Result.Succes(actor)
                        }
                        is Result.Error -> moviesParse
                    }

                }
                is Result.Error -> responseParse
            }
        }
    }

    override suspend fun getPopularActors(): Result<List<Actor>> {
        return safeCall {
            val response = service.getPopularActors()

            when (val res = response.parse()) {
                is Result.Succes -> Result.Succes(res.data.actors.map {
                    it.toActor()
                })
                is Result.Error -> res
            }
        }
    }

    /** Récupérer la liste des catégories
     * @return [Result<List<Category>>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    override suspend fun getCategories(): Result<List<Category>> {
        return safeCall {
            val response = service.getCategories()

            when (val res = response.parse()) {
                is Result.Succes -> Result.Succes(res.data.category.map {
                    it.toCategory()
                })
                is Result.Error -> res
            }
        }
    }

    /** Récupérer une catégorie
     * @return [Result<Category>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    override suspend fun getCategory(id: Int): Result<Category> {
        TODO("Not yet implemented")
    }

    /** Récupérer un film
     * @param id l'identifiant du film
     * @return [Result<Movie>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    override suspend fun getMovie(id: Int): Result<Movie> {
        return safeCall {
            val response = service.getMovie(id)
            val actorsParse = service.getMovieActors(id).parse()

            when (val responseParse = response.parse()) {
                is Result.Succes -> safeCall {
                    when (actorsParse) {
                        is Result.Succes -> safeCall {
                            val actors = actorsParse.data.credits.map {
                                it.toActor()
                            }

                            val movie = responseParse.data.toMovie()
                            movie.actors = actors

                            Result.Succes(movie)
                        }
                        is Result.Error -> actorsParse
                    }

                }
                is Result.Error -> responseParse
            }
        }
    }
    override suspend fun getMovieActors(id: Int): Result<List<Actor>> {
        TODO("Not yet implemented")
    }

    /** Récupérer la liste des films populaires
     * @return [Result<List<Movie>>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    override suspend fun getMoviesByCategory(category: Category, limit: Int): Result<List<Movie>> {
        return safeCall {
            val response = service.getMoviesByCategory(category.id)

            when (val res = response.parse()) {
                is Result.Succes -> {
                    Result.Succes(res.data.movies.map {
                        it.toMovie()
                    })
                }
                is Result.Error -> res
            }
        }
    }

    /**
     *  Récupères les films populaires
     *  @param limit le nombre de films à récupérer maximum (10 par défaut)
     *  @return [Result<List<Movie>>]
     *  Si [Result.Succes], tout s'est bien passé
     *  Sinon, une erreur est survenue
     */
    override suspend fun getPopularMovies(limit: Int): Result<List<Movie>> {
        return safeCall {
            val response = service.getPopularMovies()

            when (val res = response.parse()) {
                is Result.Succes -> Result.Succes(res.data.movies.map {
                    it.toMovie()
                })
                is Result.Error -> res
            }
        }
    }

    override suspend fun getSerie(id: Int): Result<Serie> {
        return safeCall {
            val response = service.getSerie(id)
            val actorsParse = service.getSerieActors(id).parse()

            when (val responseParse = response.parse()) {
                is Result.Succes -> safeCall {
                    when (actorsParse) {
                        is Result.Succes -> safeCall {
                            val actors = actorsParse.data.credits.map {
                                it.toActor()
                            }
                            val serie = responseParse.data.toSerie()
                            serie.actors = actors

                            Result.Succes(serie)
                        }
                        is Result.Error -> actorsParse
                    }

                }
                is Result.Error -> responseParse
            }
        }
    }

    override suspend fun getPopularSeries(limit: Int): Result<List<Serie>> {
        return safeCall {
            val response = service.getPopularSeries()

            when (val res = response.parse()) {
                is Result.Succes -> Result.Succes(res.data.series.map {
                    it.toSerie()
                })
                is Result.Error -> res
            }
        }
    }

    override suspend fun getSeriesByCategory(category: Category, limit: Int): Result<List<Serie>> {
        return safeCall {
            val response = service.getSeriesByCategory(category.id)

            when (val res = response.parse()) {
                is Result.Succes -> {
                    Result.Succes(res.data.series.map {
                        it.toSerie()
                    })
                }
                is Result.Error -> res
            }
        }
    }

    override suspend fun getSeason(tvId: Int, seasonNumber: Int): Result<Season> {
        return safeCall {
            val response = service.getSeason(tvId, seasonNumber)

                when (val res = response.parse()) {
                    is Result.Succes -> Result.Succes(res.data.toSeason())
                    is Result.Error -> res
                }
        }
    }

    override suspend fun getEpisode(
        tvId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): Result<Episode> {
        return safeCall {
            val response = service.getEpisode(tvId, seasonNumber, episodeNumber)

            when (val res = response.parse()) {
                is Result.Succes -> Result.Succes(res.data.toEpisode())
                is Result.Error -> res
            }
        }
    }


    /**
     * Récupérer le token du serveur
     * @return [Result<Token>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    override suspend fun getToken(): Result<Token> {
        return safeCall {
            val response = service.getToken()

            when (val res = response.parse()) {
                is Result.Succes -> Result.Succes(res.data.toToken())
                is Result.Error -> res
            }
        }
    }

    /**
     *  Cette fonction n'est pas implémentée car on ne peut pas sauvegarder un acteur sur le serveur
     */
    override suspend fun saveActor(actor: Actor) {
        // Cette fonction n'est pas implémentée car on ne peut pas sauvegarder un acteur sur le serveur
    }

    /**
     *  Cette fonction n'est pas implémentée car on ne peut pas sauvegarder les catégories sur le serveur
     */
    override suspend fun saveCategories(categories: List<Category>) {
        // Cette fonction n'est pas implémentée car on ne peut pas sauvegarder les catégories sur le serveur
    }

    /**
     *  Cette fonction n'est pas implémentée car on ne peut pas sauvegarder les films sur le serveur
     */
    override suspend fun saveMovie(movie: Movie) {
        // Cette fonction n'est pas implémentée car on ne peut pas sauvegarder les films sur le serveur
    }

    /**
     *  Cette fonction n'est pas implémentée car on ne peut pas sauvegarder les films sur le serveur
     */
    override suspend fun saveMovies(movies: List<Movie>) {
        // Cette fonction n'est pas implémentée car on ne peut pas sauvegarder les films sur le serveur
    }

    override suspend fun saveSerie(serie: Serie) {
        // Cette fonction n'est pas implémentée car on ne peut pas sauvegarder les séries sur le serveur
    }

    override suspend fun saveSeries(series: List<Serie>) {
        // Cette fonction n'est pas implémentée car on ne peut pas sauvegarder les séries sur le serveur
    }

    override suspend fun saveSeason(season: Season) {
        // Cette fonction n'est pas implémentée car on ne peut pas sauvegarder les saisons sur le serveur
    }

    override suspend fun saveSeasons(seasons: List<Season>) {
        // Cette fonction n'est pas implémentée car on ne peut pas sauvegarder les saisons sur le serveur
    }

    override suspend fun saveEpisode(episode: Episode) {
        // Cette fonction n'est pas implémentée car on ne peut pas sauvegarder les épisodes sur le serveur
    }

    override suspend fun saveEpisodes(episodes: List<Episode>) {
        // Cette fonction n'est pas implémentée car on ne peut pas sauvegarder les épisodes sur le serveur
    }

    /**
     *  Cette fonction n'est pas implémentée car le token est récupéré depuis le serveur
     */
    override suspend fun saveToken(token: Token) {
        // Cette fonction n'est pas implémentée car le token est récupéré depuis le serveur
    }

}

