package com.cagataykolus.newsapp.di.module

import android.app.Application
import com.cagataykolus.newsapp.data.local.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class ArticleDatabaseModule {
    @Singleton
    @Provides
    fun provideArticleDatabase(application: Application) = ArticleDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideArticleDao(database: ArticleDatabase) = database.getArticleDao()
}
