package com.akin.omdbapi.domain

import android.util.Log
import com.akin.omdbapi.data.Resource
import com.akin.omdbapi.data.api.ApiDataSource
import com.akin.omdbapi.data.api.ApiService
import com.akin.omdbapi.data.model.DetailModel
import com.akin.omdbapi.data.model.SearchModel
import com.akin.omdbapi.util.performNetworkOperation
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject


class DetailRepository @Inject constructor(
    private val api: ApiDataSource
) {
    fun searchMovieByImdbId(id: String) = performNetworkOperation {
        api.searchMovieByImdbId(id)
    }
}