package com.gmail.eamosse.imdb.ui.listing

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.gmail.eamosse.idbdata.utils.Result

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val repository: Repository
    ) : ViewModel() {

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _category: MutableLiveData<Category> = MutableLiveData()
    val category: LiveData<Category>
        get() = _category


    fun getCategory(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getCategory(id)) {
                is Result.Succes -> {
                    _category.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }


    fun getMoviesByCategory(category: Category) {

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getMoviesByCategory(category)) {
                is Result.Succes -> {
                    _movies.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
}
