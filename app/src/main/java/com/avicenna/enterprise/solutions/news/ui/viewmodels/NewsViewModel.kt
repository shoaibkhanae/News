package com.avicenna.enterprise.solutions.news.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avicenna.enterprise.solutions.news.data.api.NewsApiService
import com.avicenna.enterprise.solutions.news.data.api.RetrofitBuilder
import com.avicenna.enterprise.solutions.news.data.model.News
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private var _news = MutableLiveData<News>()
    val news: LiveData<News> = _news

    init {
        getNews()
    }

    fun getNews() {
        val api = RetrofitBuilder.getInstance().create(NewsApiService::class.java)
        viewModelScope.launch {
            try {
                val result = api.getNews()
                if (result.body() != null) {
                    _news.value = result.body()
                }
            } catch (e: Exception) {
                Log.d("Response", "Network error")
            }
        }
    }
}