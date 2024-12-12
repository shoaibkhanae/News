package com.avicenna.enterprise.solutions.news.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.avicenna.enterprise.solutions.news.data.api.NewsApiService
import com.avicenna.enterprise.solutions.news.data.model.News

class NewsRepository(private val apiService: NewsApiService) {
    private val _news = MutableLiveData<News>()
    val news: LiveData<News> = _news

    private val _category = MutableLiveData<News>()
    val category: LiveData<News> = _category

    suspend fun getNews() {
        try {
            val result = apiService.getNews()
            if (result.body() != null) {
                _news.postValue(result.body())
            }
        } catch (e: Exception) {
            Log.d("Response", "Network error")
        }
    }

    suspend fun getNewsWithCategory(category: String) {
        try {
            val result = apiService.getCategoryNews(category = category)
            if (result.body() != null) {
                _category.postValue(result.body())
            }
        } catch (e: Exception) {
            Log.d("Response", "Network error")
        }
    }
}