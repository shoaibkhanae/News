package com.avicenna.enterprise.solutions.news.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avicenna.enterprise.solutions.news.data.model.News
import com.avicenna.enterprise.solutions.news.data.repository.NewsRepository
import com.avicenna.enterprise.solutions.news.utils.Response
import kotlinx.coroutines.launch

class SearchNewsViewModel(private val repository: NewsRepository) : ViewModel() {
    val searched: LiveData<Response<News>> = repository.searched

    init {
        searchNewsWithCategory("general")
    }

    fun searchNews(search: String) {
        viewModelScope.launch {
            repository.searchNews(search)
        }
    }

    fun searchNewsWithCategory(category: String) {
        viewModelScope.launch {
            repository.searchNewsWithCategory(category)
        }
    }
}