package com.akin.omdbapi.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akin.omdbapi.data.Resource
import com.akin.omdbapi.data.model.DetailModel
import com.akin.omdbapi.data.model.SearchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
) : ViewModel() {

    fun searchMovieById(imdbId: String): LiveData<Resource<DetailModel>>{
        return detailRepository.searchMovieByImdbId(imdbId)
    }
}