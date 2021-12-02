package com.akin.omdbapi.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akin.omdbapi.data.Resource
import com.akin.omdbapi.data.model.Search
import com.akin.omdbapi.data.model.SearchModel
import com.bumptech.glide.load.HttpException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    var pageNumber = 1
    fun getMoviesByQuery(title: String): LiveData<Resource<SearchModel>> {
        return searchRepository.searchMoviesByName(title, pageNumber)
    }
}