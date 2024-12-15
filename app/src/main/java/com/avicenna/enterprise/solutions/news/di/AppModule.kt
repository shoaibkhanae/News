package com.avicenna.enterprise.solutions.news.di

import android.content.Context
import androidx.room.Room
import com.avicenna.enterprise.solutions.news.data.api.NewsApiService
import com.avicenna.enterprise.solutions.news.data.db.ArticleDao
import com.avicenna.enterprise.solutions.news.data.db.ArticleDatabase
import com.avicenna.enterprise.solutions.news.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideArticleDao(database: ArticleDatabase): ArticleDao {
        return database.getArticleDao()
    }

    @Provides
    @Singleton
    fun providesArticleDatabase(@ApplicationContext appContext: Context): ArticleDatabase {
        return Room.databaseBuilder(
            appContext,
            ArticleDatabase::class.java,
            "article_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesBaseUrl(): String = AppConstants.BASE_URL

    @Provides
    @Singleton
    fun providesRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): NewsApiService = retrofit.create(NewsApiService::class.java)

}