package com.cagataykolus.newsapp.data.repository

import com.cagataykolus.newsapp.data.local.dao.ArticleDao
import com.cagataykolus.newsapp.data.remote.api.NewsApiService
import com.cagataykolus.newsapp.model.Article
import com.cagataykolus.newsapp.model.News
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
interface ArticleRepository {
    fun getAllArticles(): Flow<Resource<List<Article>>>
    fun deleteAllArticles(): Flow<Resource<List<Article>>>
}

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is single source of data.
 */
@ExperimentalCoroutinesApi
class DefaultArticleRepository @Inject constructor(
    private val dao: ArticleDao,
    private val service: NewsApiService
) : ArticleRepository {
    /**
     * Fetched the data from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    override fun getAllArticles(): Flow<Resource<List<Article>>> {
        return object : NetworkBoundRepository<List<Article>, News>() {

            override suspend fun saveRemoteData(response: News) =
                dao.addArticles(response.articles)

            override fun fetchFromLocal(): Flow<List<Article>> {
                return dao.getAllArticles()
            }

            override suspend fun fetchFromRemote(): Response<News> = service.getNews()
        }.asFlow()
    }

    /**
     * Deletes all data.
     */
    override fun deleteAllArticles(): Flow<Resource<List<Article>>> {
        return object : NetworkBoundRepository<List<Article>, News>() {

            override suspend fun saveRemoteData(response: News) = dao.deleteAllArticles()

            override fun fetchFromLocal(): Flow<List<Article>> {
                return dao.getAllArticles()
            }

            override suspend fun fetchFromRemote(): Response<News> = service.getNews()

        }.asFlow()
    }
}