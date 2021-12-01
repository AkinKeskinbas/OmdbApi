package com.akin.omdbapi.data.api

import com.akin.omdbapi.data.Resource
import com.akin.omdbapi.data.model.Search
import com.akin.omdbapi.data.model.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?apikey=147ac4d6")
    suspend fun searchMovie(
        @Query("s") movieName:String,
        @Query("page") page: Int

    ) :Response<SearchModel>
}