package com.avicenna.enterprise.solutions.news.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.avicenna.enterprise.solutions.news.data.models.Article

@Database(
    entities = [Article::class],
    exportSchema = false,
    version = 6
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao
}