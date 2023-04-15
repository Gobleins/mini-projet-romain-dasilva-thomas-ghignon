package com.gmail.eamosse.imdb.ui.actorDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Actor
import com.gmail.eamosse.idbdata.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.gmail.eamosse.idbdata.utils.Result

@HiltViewModel
class ActorDetailViewModel @Inject constructor(
    private val repository: MovieRepository
    ) : ViewModel() {

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    private val _actor: MutableLiveData<Actor> = MutableLiveData()
    val actor: LiveData<Actor>
        get() = _actor

    fun getDetailActor(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getActor(id)) {
                is Result.Succes -> {
                    _actor.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.exception.message)
                }
            }
        }
    }
}
