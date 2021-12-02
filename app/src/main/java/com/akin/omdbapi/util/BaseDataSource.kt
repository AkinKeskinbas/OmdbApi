package com.akin.omdbapi.util


import com.akin.omdbapi.data.Resource
import com.akin.omdbapi.data.model.SearchModel
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                val successCallError = SearchModel("False", null, null)
                return if (body != null && !body.equals(successCallError)) {
                    Resource.success(body)
                } else {
                    Resource.error("Opps, Somethings went wrong!")
                }
            }
            val errorBody = response.errorBody().toString()
            return error("${response.code()} - $errorBody")
        } catch (err: Exception) {
            return error("${err.localizedMessage} - ${err.message}")
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error("Network error: $message")
    }
}