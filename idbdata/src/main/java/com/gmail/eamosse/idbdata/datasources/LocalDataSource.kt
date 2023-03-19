package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.api.safeCall
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.data.toEntity
import com.gmail.eamosse.idbdata.local.daos.ActorDao
import com.gmail.eamosse.idbdata.local.daos.CategoryDao
import com.gmail.eamosse.idbdata.local.daos.MovieDao
import com.gmail.eamosse.idbdata.local.daos.TokenDao
import com.gmail.eamosse.idbdata.local.entities.*
import com.gmail.eamosse.idbdata.local.entities.toCategory
import com.gmail.eamosse.idbdata.local.entities.toToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import com.gmail.eamosse.idbdata.utils.Result

@Singleton
internal class LocalDataSource @Inject constructor(
    private val tokenDao: TokenDao,
    private val categoryDao: CategoryDao,
    private val movieDao: MovieDao,
    private val actorDao: ActorDao) : MovieDataSource{


    /**
     * Récupère un acteur enregistré en local
     * @param id l'identifiant de l'acteur
     * @return [Result<Actor>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    override suspend fun getActor(id: Int): Result<Actor> = withContext(Dispatchers.IO) {
        safeCall {
            actorDao.getActor(id)?.let {
                Result.Succes(it.toActor())
            } ?: Result.Error(
                exception = Exception(),
                message = "Actor not found",
                code = -1
            )
        }
    }

    /**
     * Récupère la liste des catégories enregistrées en local
     * @return [Result<List<Category>>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    override suspend fun getCategories(): Result<List<Category>> = withContext(Dispatchers.IO) {
        val categories = categoryDao.getAll().map {
            it.toCategory()
        }
        Result.Succes(categories)
    }

    /**
     * Récupère un film enregistré en local
     * @param id l'identifiant du film
     * @return [Result<Movie>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    override suspend fun getMovie(id: Int): Result<Movie> = withContext(Dispatchers.IO) {
        safeCall {
            movieDao.getMovie(id)?.let {
                Result.Succes(it.toMovie())
            } ?: Result.Error(
                exception = Exception(),
                message = "Movie not found",
                code = -1
            )
        }

    }

    override suspend fun getMovieActors(id: Int): Result<List<Actor>> {
        TODO("Not yet implemented")
    }

    /**
     * Récupère la liste des films d'une catégorie enregistrés en local
     * @param category la catégorie des films à récupérer
     * @return [Result<List<Movie>>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    override suspend fun getMoviesByCategory(category: Category, limit: Int): Result<List<Movie>> = withContext(Dispatchers.IO) {
        safeCall {
            val movies = movieDao.getMoviesByCategory(category, limit).map {
                it.toMovie()
            }
            Result.Succes(movies)
        }
    }

    /**
     * Récupère la liste des films populaires enregistrés en local
     * @return [Result<List<Movie>>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    override suspend fun getPopularMovies(limit: Int): Result<List<Movie>> = withContext(Dispatchers.IO) {
        safeCall {
            val movies = movieDao.getPopularMovies(limit).map {
                it.toMovie()
            }
            Result.Succes(movies)
        }
    }

    /**
     * Récupère le token enregistré en local
     */
    override suspend fun getToken(): Result<Token> = withContext(Dispatchers.IO) {
        tokenDao.retrieve()?.let {
            Result.Succes(it.toToken())
        } ?: Result.Error(
            exception = Exception(),
            message = "You should get a token from the API first",
            code = -1
        )
    }

    /**
     * Enregistre un acteur en local
     * @param actor l'acteur à enregistrer
     */
    override suspend fun saveActor(actor: Actor) = withContext(Dispatchers.IO) {
        actorDao.saveActor(actor.toEntity()).also {
            actor.movies.onEach {
                actorDao.saveMovies(listOf(it.toEntity()))
            }
        }
    }

    /**
     * Enregistre la liste des catégories en local
     * @param categories la liste des catégories à enregistrer
     * @return [Result<Unit>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    override suspend fun saveCategories(categories: List<Category>) = withContext(Dispatchers.IO) {
        categoryDao.saveCategories(categories.map {
            it.toEntity()
        })
    }

    /**
     * Enregistre un film en local
     * @param movie le film à enregistrer
     * @return [Result<Unit>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    override suspend fun saveMovie(movie: Movie) = withContext(Dispatchers.IO){
        movieDao.saveMovie(movie.toEntity()).also {
            movie.actors.onEach {
                movieDao.saveActors(listOf(it.toEntity()))
                movieDao.saveActorMovieCrassRef(
                    ActorMovieCrossRef(
                        movieId = movie.id,
                        actorId = it.id
                    )
                )
            }
        }

    }
    override suspend fun saveMovies(movies: List<Movie>) {
        movieDao.saveMovies(movies.map{
            it.toEntity()
        })
    }

    /**
     * Enregistre le token en local
     */
    override suspend fun saveToken(token: Token) {
        withContext(Dispatchers.IO) {
            tokenDao.insert(token.toEntity())
        }
    }











}
