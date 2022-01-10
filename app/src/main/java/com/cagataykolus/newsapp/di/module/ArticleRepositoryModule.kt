package com.cagataykolus.newsapp.di.module

import com.cagataykolus.newsapp.data.repository.DefaultArticleRepository
import com.cagataykolus.newsapp.data.repository.ArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class ArticleRepositoryModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindArticleRepository(repository: DefaultArticleRepository): ArticleRepository
}