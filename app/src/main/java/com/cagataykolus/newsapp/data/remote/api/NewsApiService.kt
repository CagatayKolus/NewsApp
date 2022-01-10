package com.cagataykolus.newsapp.data.remote.api

import com.cagataykolus.newsapp.BuildConfig
import com.cagataykolus.newsapp.model.News
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
/**
 * Service to fetch data using endpoint [NEWS_API_URL].
 */
interface NewsApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("apiKey") key: String = BuildConfig.NEWS_API_KEY,
        @Query("country") query: String = BuildConfig.NEWS_COUNTRY_CODE
    ): Response<News>

    companion object {
        const val NEWS_API_URL = "https://newsapi.org/v2/"
    }
}