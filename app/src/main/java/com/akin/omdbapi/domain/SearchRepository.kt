package com.akin.omdbapi.domain

import android.util.Log
import com.akin.omdbapi.data.Resource
import com.akin.omdbapi.data.api.ApiService
import com.akin.omdbapi.data.model.SearchModel
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.log

@ActivityScoped
class SearchRepository @Inject constructor(
    private val api:ApiService
) {
    suspend fun searchMovie(movieName:String,page:Int) :Resource<SearchModel> {
        val response = try {

            api.searchMovie(movieName,page)
        }catch (e: Exception){

            Log.d("repository",e.message.toString())
            return  Resource.Error(e.message,null)
        }

        return Resource.Success(response.body()!!)
    }
}