package com.akin.omdbapi.data.api

import com.akin.omdbapi.data.model.DetailModel
import com.akin.omdbapi.data.model.SearchModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?apikey=147ac4d6")
    suspend fun searchMovie(
        @Query("s") movieName: String,
        @Query("page") page: Int

    ): Response<SearchModel>

    @GET("?apikey=147ac4d6")
    suspend fun searchMovieById(
        @Query("i") movieName: String,

        ): Response<DetailModel>

}