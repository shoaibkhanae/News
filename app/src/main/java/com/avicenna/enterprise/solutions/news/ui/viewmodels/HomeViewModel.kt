package com.avicenna.enterprise.solutions.news.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avicenna.enterprise.solutions.news.data.model.Article
import com.avicenna.enterprise.solutions.news.data.model.News
import com.avicenna.enterprise.solutions.news.data.repository.NewsRepository
import com.avicenna.enterprise.solutions.news.utils.Response
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: NewsRepository) : ViewModel() {
    val news: LiveData<Response<News>> = repository.news
    val category: LiveData<Response<News>> = repository.category

    private val _selected = MutableLiveData<Article>()
    val selected: LiveData<Article> = _selected

    fun select(article: Article) {
        _selected.value = article
    }

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