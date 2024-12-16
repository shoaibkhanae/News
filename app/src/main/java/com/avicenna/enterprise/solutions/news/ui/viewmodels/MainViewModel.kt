package com.avicenna.enterprise.solutions.news.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.avicenna.enterprise.solutions.news.data.models.Article
import com.avicenna.enterprise.solutions.news.data.models.News
import com.avicenna.enterprise.solutions.news.data.repository.NewsRepository
import com.avicenna.enterprise.solutions.news.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    val news: LiveData<Response<News>> = repository.news
    val category: LiveData<Response<News>> = repository.category
    val searched: LiveData<Response<News>> = repository.searched
    val favorites: LiveData<List<Article>> = repository.articles.asLiveData()
    private val _existing = MutableLiveData<List<Article>>()
    val existing: LiveData<List<Article>> = _existing

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


    fun insert(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(article)
        }
    }

    fun delete(article: Article) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(article)
        }
    }

    fun checkArticleExists(title: String) {
        viewModelScope.launch {
            _existing.value = repository.checkArticleExists(title)
        }
    }
}