package com.avicenna.enterprise.solutions.news.data.api

import com.avicenna.enterprise.solutions.news.data.model.News
import com.avicenna.enterprise.solutions.news.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("apikey") apiKey: String = AppConstants.API_KEY
    ): Response<News>


}