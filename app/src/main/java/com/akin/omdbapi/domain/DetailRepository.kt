package com.akin.omdbapi.domain

import com.akin.omdbapi.data.api.ApiDataSource
import com.akin.omdbapi.util.performNetworkOperation
import javax.inject.Inject


class DetailRepository @Inject constructor(
    private val api: ApiDataSource
) {
    fun searchMovieByImdbId(id: String) = performNetworkOperation {
        api.searchMovieByImdbId(id)
    }
}