package com.gmail.eamosse.imdb.ui.serieDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Episode
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.Season
import com.gmail.eamosse.idbdata.data.Serie
import com.gmail.eamosse.idbdata.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.gmail.eamosse.idbdata.utils.Result

@HiltViewModel
class SerieDetailViewModel @Inject constructor(
    private val repository: Repository
    ) : ViewModel() {

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    private val _serie: MutableLiveData<Serie> = MutableLiveData()
    val serie: LiveData<Serie>
        get() = _serie

    private val _season: MutableLiveData<Season> = MutableLiveData()
    val season: LiveData<Season>
        get() = _season


    fun getDetailSerie(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getSerie(id)) {
                is Result.Succes -> {
                    _serie.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getSeason(id: Int, seasonNumber: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getSeason(id, seasonNumber)) {
                is Result.Succes -> {
                    _season.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
}
