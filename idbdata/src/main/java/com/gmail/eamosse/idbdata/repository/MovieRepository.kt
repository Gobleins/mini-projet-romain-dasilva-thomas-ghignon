package com.gmail.eamosse.idbdata.repository

import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineDataSource
import com.gmail.eamosse.idbdata.utils.Result
import javax.inject.Inject

/**
 * La classe permettant de gérer les données de l'application
 */
class MovieRepository @Inject internal constructor(
    private val local: LocalDataSource,
    private val online: OnlineDataSource
) {

    suspend fun getActor(id: Int): Result<Actor> {
        return online.getActor(id).also {
            if (it is Result.Succes) {
                local.saveActor(it.data)
            } else {
                val actor = local.getActor(id)
                if (actor != null) {
                    return actor
                }
            }
        }
    }

    suspend fun getPopularActors(): Result<List<Actor>> {
        return when(val result = online.getPopularActors()) {
            is Result.Succes -> {
                val actors = result.data
                //local.saveActor(actors)
                Result.Succes(actors)
            }
            is Result.Error -> result
        }
    }

    suspend fun getCategories(): Result<List<Category>> {
        return when(val result = online.getCategories()) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val categories = result.data
                local.saveCategories(categories)
                Result.Succes(categories)
            }
            is Result.Error -> result
        }
    }

    suspend fun getCategory(id: Int): Result<Category> {
        //TODO: récupérer la catégorie online
        return local.getCategory(id).also { localCategory ->
            if (localCategory is Result.Error) {
                //TODO: save la catégorie online
            }
        }
    }

    suspend fun getMovie(id: Int): Result<Movie> {
        return online.getMovie(id).also { onlineMovie ->
            if (onlineMovie is Result.Succes) {
                local.getMovie(id).also { localMovie ->
                    if (localMovie is Result.Error) {
                        local.saveMovie(onlineMovie.data)
                    }
                }
            } else {
                val movie = local.getMovie(id)
                if (movie != null) {
                    return movie
                }
            }
        }
    }

    suspend fun getHighlightMovie(): Result<Movie> {
        return local.getHighlightMovie()
    }

    suspend fun getMoviesByCategory(category: Category): Result<List<Movie>> {
        return online.getMoviesByCategory(category, 10).also {
            if (it is Result.Succes) {
                local.saveMovies(it.data)
            } else {
                val movies = local.getMoviesByCategory(category)
                if (movies != null) {
                    return movies
                }
            }
        }
    }

    suspend fun getPopularMovies(): Result<List<Movie>> {
        return when(val result = online.getPopularMovies()) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val movies = result.data
                local.saveMovies(movies)
                Result.Succes(movies)
            }
            is Result.Error -> result
        }
    }


    /**
     * Récupérer le token permettant de consommer les ressources sur le serveur
     * Le résultat du datasource est converti en instance d'objets publiques
     */
    suspend fun getToken(): Result<Token> {

        return when (val result = online.getToken()) {
            is Result.Succes -> {
                //save the response in the local database
                local.saveToken(result.data)
                //return the response
                Result.Succes(result.data)
            }
            is Result.Error -> result
        }
    }

}