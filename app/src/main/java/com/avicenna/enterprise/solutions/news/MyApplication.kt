package com.avicenna.enterprise.solutions.news

import android.app.Application
import com.avicenna.enterprise.solutions.news.data.api.NewsApiService
import com.avicenna.enterprise.solutions.news.data.api.RetrofitBuilder
import com.avicenna.enterprise.solutions.news.data.local.ArticleDatabase
import com.avicenna.enterprise.solutions.news.data.repository.NewsRepository

class MyApplication : Application() {
    private val api by lazy { RetrofitBuilder.getInstance().create(NewsApiService::class.java) }
    private val db by lazy { ArticleDatabase.getDatabase(this) }
    private val dao by lazy { db.getArticleDao() }
    val repository by lazy { NewsRepository(api, dao) }
}