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
    private val _response = MutableLiveData<SearchModel>()
    val response: LiveData<SearchModel> = _response
    var errorMessage = MutableLiveData("")
    var isLoading = MutableLiveData(false)


    fun getMovies(movieName: String) {
        isLoading.value = true
        viewModelScope.launch {
            var pageNumber = 1

            when (val result = searchRepository.searchMovie(movieName, pageNumber)) {
                is Resource.Error -> {
                    println(result.data?.Response)
                    errorMessage.value = result.message!!
                    isLoading.value = false
                    _response.value = null

                }
                is Resource.Success -> {
                    //Fake delay
                    delay(1000)
                    _response.value = result.data
                    errorMessage.value = ""
                    isLoading.value = false
                }
            }
        }
    }
}