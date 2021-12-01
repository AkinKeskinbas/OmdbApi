package com.akin.omdbapi.data.api

import com.akin.omdbapi.util.BaseDataSource
import javax.inject.Inject

class ApiDataSource
@Inject constructor(private val apiService: ApiService): BaseDataSource(){

    suspend fun searchMoviesByName(title: String,page: Int) = getResult {
        apiService.searchMovie(title,page)
    }

    suspend fun searchMovieByImdbId(id: String) = getResult {
        apiService.searchMovieById(id)
    }
}