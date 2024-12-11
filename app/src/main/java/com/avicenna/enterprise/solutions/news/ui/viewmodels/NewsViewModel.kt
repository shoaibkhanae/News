package com.avicenna.enterprise.solutions.news.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avicenna.enterprise.solutions.news.data.model.News
import com.avicenna.enterprise.solutions.news.data.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    val news: LiveData<News> = repository.news

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch {
            repository.getNews()
        }
    }
}