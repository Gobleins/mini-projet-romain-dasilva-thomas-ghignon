package com.gmail.eamosse.idbdata.datasources

import com.gmail.eamosse.idbdata.api.safeCall
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.data.toEntity
import com.gmail.eamosse.idbdata.local.daos.*
import com.gmail.eamosse.idbdata.local.daos.ActorDao
import com.gmail.eamosse.idbdata.local.daos.CategoryDao
import com.gmail.eamosse.idbdata.local.daos.MovieDao
import com.gmail.eamosse.idbdata.local.daos.TokenDao
import com.gmail.eamosse.idbdata.local.entities.*
import com.gmail.eamosse.idbdata.local.entities.toCategory
import com.gmail.eamosse.idbdata.local.entities.toToken
import com.gmail.eamosse.idbdata.utils.Result
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
internal class LocalDataSource
@Inject
constructor(
    private val tokenDao: TokenDao,
    private val categoryDao: CategoryDao,
    private val movieDao: MovieDao,
    private val actorDao: ActorDao,
    private val serieDao: SerieDao
) : MovieDataSource {

  /**
   * Récupère un acteur enregistré en local
   *
   * @param id l'identifiant de l'acteur
   * @return [Result<Actor>] Si [Result.Succes], tout s'est bien passé Sinon, une erreur est
   *   survenue
   */
  override suspend fun getActor(id: Int): Result<Actor> =
      withContext(Dispatchers.IO) {
        safeCall {
          actorDao.getActor(id)?.let { Result.Succes(it.toActor()) }
              ?: Result.Error(exception = Exception(), message = "Actor not found", code = -1)
        }
      }

  override suspend fun getPopularActors(): Result<List<Actor>> =
      withContext(Dispatchers.IO) {
        val actors = actorDao.getPopularActors().map { it.toActor() }
        Result.Succes(actors)
      }

  /**
   * Récupère la liste des catégories enregistrées en local
   *
   * @return [Result<List<Category>>] Si [Result.Succes], tout s'est bien passé Sinon, une erreur
   *   est survenue
   */
  override suspend fun getCategories(): Result<List<Category>> =
      withContext(Dispatchers.IO) {
        val categories = categoryDao.getAll().map { it.toCategory() }
        Result.Succes(categories)
      }

  /**
   * Récupère une catégorie enregistrée en local
   *
   * @return [Result<Category>] Si [Result.Succes], tout s'est bien passé Sinon, une erreur est
   *   survenue
   */
  override suspend fun getCategory(id: Int): Result<Category> =
      withContext(Dispatchers.IO) {
        safeCall {
          categoryDao.getCategory(id)?.let { Result.Succes(it.toCategory()) }
              ?: Result.Error(exception = Exception(), message = "Category not found", code = -1)
        }
      }

  /**
   * Récupère un film enregistré en local
   *
   * @param id l'identifiant du film
   * @return [Result<Movie>] Si [Result.Succes], tout s'est bien passé Sinon, une erreur est
   *   survenue
   */
  override suspend fun getMovie(id: Int): Result<Movie> =
      withContext(Dispatchers.IO) {
        safeCall {
          movieDao.getMovie(id)?.let { Result.Succes(it.toMovie()) }
              ?: Result.Error(exception = Exception(), message = "Movie not found", code = -1)
        }
      }

  override suspend fun getMovieActors(id: Int): Result<List<Actor>> {
    return safeCall { movieDao.getMovieActors(id).map { it.toActor() }.let { Result.Succes(it) } }
  }

  /**
   * Récupère la liste des films d'une catégorie enregistrés en local
   *
   * @param category la catégorie des films à récupérer
   * @return [Result<List<Movie>>] Si [Result.Succes], tout s'est bien passé Sinon, une erreur est
   *   survenue
   */
  override suspend fun getMoviesByCategory(category: Category, limit: Int): Result<List<Movie>> =
      withContext(Dispatchers.IO) {
        safeCall {
          val movies = movieDao.getMoviesByCategory(category.id, limit).map { it.toMovie() }
          Result.Succes(movies)
        }
      }

  /**
   * Récupère la liste des films populaires enregistrés en local
   *
   * @return [Result<List<Movie>>] Si [Result.Succes], tout s'est bien passé Sinon, une erreur est
   *   survenue
   */
  override suspend fun getPopularMovies(limit: Int): Result<List<Movie>> =
      withContext(Dispatchers.IO) {
        safeCall {
          val movies = movieDao.getPopularMovies(limit).map { it.toMovie() }
          Result.Succes(movies)
        }
      }

  override suspend fun getSerie(id: Int): Result<Serie> =
      withContext(Dispatchers.IO) {
        safeCall {
          serieDao.getSerie(id)?.let { Result.Succes(it.toSerie()) }
              ?: Result.Error(exception = Exception(), message = "Serie not found", code = -1)
        }
      }

  override suspend fun getPopularSeries(limit: Int): Result<List<Serie>> =
      withContext(Dispatchers.IO) {
        safeCall {
          val series = serieDao.getPopularSeries(limit).map { it.toSerie() }
          Result.Succes(series)
        }
      }

  override suspend fun getSeriesByCategory(category: Category, limit: Int): Result<List<Serie>> =
      withContext(Dispatchers.IO) {
        safeCall {
          val series = serieDao.getSeriesByCategory(category.id, limit).map { it.toSerie() }
          Result.Succes(series)
        }
      }

  override suspend fun getSeason(tvId: Int, seasonNumber: Int): Result<Season> =
      withContext(Dispatchers.IO) {
        safeCall {
          serieDao.getSeason(tvId, seasonNumber)?.let { Result.Succes(it.toSeason()) }
              ?: Result.Error(exception = Exception(), message = "Season not found", code = -1)
        }
      }

  override suspend fun getEpisode(
      tvId: Int,
      seasonNumber: Int,
      episodeNumber: Int
  ): Result<Episode> =
      withContext(Dispatchers.IO) {
        safeCall {
          serieDao.getEpisode(tvId, seasonNumber, episodeNumber)?.let {
            Result.Succes(it.toEpisode())
          }
              ?: Result.Error(exception = Exception(), message = "Episode not found", code = -1)
        }
      }

  /** Récupère le token enregistré en local */
  override suspend fun getToken(): Result<Token> =
      withContext(Dispatchers.IO) {
        tokenDao.retrieve()?.let { Result.Succes(it.toToken()) }
            ?: Result.Error(
                exception = Exception(),
                message = "You should get a token from the API first",
                code = -1)
      }

  /**
   * Enregistre un acteur en local
   *
   * @param actor l'acteur à enregistrer
   */
  override suspend fun saveActor(actor: Actor) =
      withContext(Dispatchers.IO) {
        actorDao.saveActor(actor.toEntity()).also {
          actor.movies.onEach { actorDao.saveMovies(listOf(it.toEntity())) }
        }
      }

  /**
   * Enregistre la liste des catégories en local
   *
   * @param categories la liste des catégories à enregistrer
   * @return [Result<Unit>] Si [Result.Succes], tout s'est bien passé Sinon, une erreur est survenue
   */
  override suspend fun saveCategories(categories: List<Category>) =
      withContext(Dispatchers.IO) { categoryDao.saveCategories(categories.map { it.toEntity() }) }

  /**
   * Enregistre un film en local
   *
   * @param movie le film à enregistrer
   * @return [Result<Unit>] Si [Result.Succes], tout s'est bien passé Sinon, une erreur est survenue
   */
  override suspend fun saveMovie(movie: Movie) =
      withContext(Dispatchers.IO) {
        movieDao.saveMovie(movie.toEntity()).also {
          movie.actors.onEach {
            movieDao.saveActors(listOf(it.toEntity()))
            movieDao.saveActorMovieCrossRef(
                ActorMovieCrossRef(movie_id = movie.id, actor_id = it.id))
          }
        }
      }
  override suspend fun saveMovies(movies: List<Movie>) {
    movieDao.saveMovies(movies.map { it.toEntity() })
  }

  override suspend fun saveSerie(serie: Serie) {
    serieDao.saveSerie(serie.toEntity())
  }

  override suspend fun saveSeries(series: List<Serie>) {
    serieDao.saveSeries(series.map { it.toEntity() })
  }

  override suspend fun saveSeason(season: Season) {
    serieDao.saveSeason(season.toEntity())
  }

  override suspend fun saveSeasons(seasons: List<Season>) {
    serieDao.saveSeasons(seasons.map { it.toEntity() })
  }

  override suspend fun saveEpisode(episode: Episode) {
    serieDao.saveEpisode(episode.toEntity())
  }

  override suspend fun saveEpisodes(episodes: List<Episode>) {
    serieDao.saveEpisodes(episodes.map { it.toEntity() })
  }

  /** Enregistre le token en local */
  override suspend fun saveToken(token: Token) {
    withContext(Dispatchers.IO) { tokenDao.insert(token.toEntity()) }
  }
}
