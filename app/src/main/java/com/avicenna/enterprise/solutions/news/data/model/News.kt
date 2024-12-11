package com.avicenna.enterprise.solutions.news.data.model

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)