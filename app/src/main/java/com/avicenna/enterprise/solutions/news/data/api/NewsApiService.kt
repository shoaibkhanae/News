package com.avicenna.enterprise.solutions.news.data.api

import com.avicenna.enterprise.solutions.news.data.models.News
import com.avicenna.enterprise.solutions.news.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") countryCode: String = "us",
        @Query("apikey") apiKey: String = AppConstants.API_KEY
    ): Response<News>


    @GET("top-headlines")
    suspend fun getCategoryNews(
        @Query("country") countryCode: String = "us",
        @Query("apikey") apiKey: String = AppConstants.API_KEY,
        @Query("category") category: String
    ): Response<News>

    @GET("everything")
    suspend fun searchNews(
        @Query("q") search: String,
        @Query("apikey") apiKey: String = AppConstants.API_KEY
    ): Response<News>



}