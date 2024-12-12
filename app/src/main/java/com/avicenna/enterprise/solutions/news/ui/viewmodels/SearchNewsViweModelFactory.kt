package com.avicenna.enterprise.solutions.news.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avicenna.enterprise.solutions.news.data.repository.NewsRepository

class SearchNewsViweModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchNewsViewModel::class.java)) {
            return SearchNewsViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown viewmdoel class")
    }
}