package com.cagataykolus.newsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cagataykolus.newsapp.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
/**
 * Data Access Object (DAO) for [com.cagataykolus.newsapp.data.local.ArticleDatabase]
 */
@Dao
interface ArticleDao {
    /**
     * Inserts [article] into the [Article.TABLE_ARTICLE] table.
     * Duplicate values are replaced in the table.
     * @param article
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(article: List<Article>)

    /**
     * Fetches all the data from the [Article.TABLE_ARTICLE] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Article.TABLE_ARTICLE}")
    fun getAllArticles(): Flow< List<Article>>

    /**
     * Deletes all the data from the [Article.TABLE_ARTICLE] table.
     */
    @Query("DELETE FROM ${Article.TABLE_ARTICLE}")
    suspend fun deleteAllArticles()
}