package com.avicenna.enterprise.solutions.news.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avicenna.enterprise.solutions.news.data.model.News
import com.avicenna.enterprise.solutions.news.data.repository.NewsRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: NewsRepository) : ViewModel() {
    val news: LiveData<News> = repository.news
    val category: LiveData<News> = repository.category

    init {
        getNews()
        getNewsWithCategory("business")
    }

    fun getNews() {
        viewModelScope.launch {
            repository.getNews()
        }
    }

    fun getNewsWithCategory(category: String) {
        viewModelScope.launch {
            repository.getNewsWithCategory(category)
        }
    }
}