package com.gmail.eamosse.imdb.ui.movieDetail

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {

    //TODO: Implement the ViewModel

//    private val _movie = MutableLiveData<Movie>()
//    val movie: LiveData<Movie> = _movie
//
//    private val _error = MutableLiveData<String>()
//    val error: LiveData<String> = _error
//
//    fun getMovieDetails(movieId: Int) {
//        viewModelScope.launch {
//            try {
//                val movie = repository.getMovie(movieId)
//                _movie.postValue(movie)
//            } catch (e: Exception) {
//                _error.postValue(e.message)
//            }
//        }
//    }
}
