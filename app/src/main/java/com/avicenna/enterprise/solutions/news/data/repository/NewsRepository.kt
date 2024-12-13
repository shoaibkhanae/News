package com.avicenna.enterprise.solutions.news.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.avicenna.enterprise.solutions.news.data.api.NewsApiService
import com.avicenna.enterprise.solutions.news.data.model.News
import com.avicenna.enterprise.solutions.news.utils.Response

class NewsRepository(private val apiService: NewsApiService) {
    private val _news = MutableLiveData<Response<News>>()
    val news: LiveData<Response<News>> = _news

    private val _category = MutableLiveData<Response<News>>()
    val category: LiveData<Response<News>> = _category

    private val _searched = MutableLiveData<Response<News>>()
    val searched: LiveData<Response<News>> = _searched

    suspend fun getNews() {
        _news.postValue(Response.Loading())
        try {
            val result = apiService.getNews()
            if (result.body() != null) {
                _news.postValue(Response.Success(result.body()))
            }
        } catch (e: Exception) {
            _news.postValue(Response.Error(e.message.toString()))
        }
    }

    suspend fun getNewsWithCategory(category: String) {
        _category.postValue(Response.Loading())
        try {
            val result = apiService.getCategoryNews(category = category)
            if (result.body() != null) {
                _category.postValue(Response.Success(result.body()))
            }
        } catch (e: Exception) {
            _category.postValue(Response.Error(e.message.toString()))
        }
    }

    suspend fun searchNews(search: String) {
        _searched.postValue(Response.Loading())
        try {
            val result = apiService.searchNews(search)
            if (result.body() != null) {
                _searched.postValue(Response.Success(result.body()))
            }
        } catch (e: Exception) {
            _searched.postValue(Response.Error(e.message.toString()))
        }
    }

    suspend fun searchNewsWithCategory(category: String) {
        _searched.postValue(Response.Loading())
        try {
            val result = apiService.getCategoryNews(category = category)
            if (result.body() != null) {
                _searched.postValue(Response.Success(result.body()))
            }
        } catch (e: Exception) {
            _searched.postValue(Response.Error(e.message.toString()))
        }
    }
}