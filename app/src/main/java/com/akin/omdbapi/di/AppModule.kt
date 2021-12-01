package com.akin.omdbapi.di

import com.akin.omdbapi.data.api.ApiService
import com.akin.omdbapi.domain.SearchRepository
import com.akin.omdbapi.util.Statics.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSearchRepository(
        apiService: ApiService
    ) = SearchRepository(apiService)

    @Provides
    @Singleton
    fun provideRetrofitInstance(): ApiService =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)

}