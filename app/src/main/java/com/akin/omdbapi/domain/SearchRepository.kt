package com.akin.omdbapi.domain

import android.util.Log
import com.akin.omdbapi.data.Resource
import com.akin.omdbapi.data.api.ApiDataSource
import com.akin.omdbapi.data.api.ApiService
import com.akin.omdbapi.data.model.SearchModel
import com.akin.omdbapi.util.performNetworkOperation
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.log


class SearchRepository @Inject constructor(
    private val api: ApiDataSource
) {
    fun searchMoviesByName(title: String, page: Int) = performNetworkOperation {

        api.searchMoviesByName(title, page)
    }


}