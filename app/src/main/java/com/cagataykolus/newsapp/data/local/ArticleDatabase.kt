package com.cagataykolus.newsapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cagataykolus.newsapp.data.local.dao.ArticleDao
import com.cagataykolus.newsapp.model.Article

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
/**
 * ArticleDatabase provides DAO [ArticleDao] by using method [getArticleDao].
 */
@Database(
    entities = [Article::class],
    version = MigrationDatabase.DB_VERSION
)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        private const val DB_NAME = "database_articles"

        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getInstance(context: Context): ArticleDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    DB_NAME
                ).addMigrations(*MigrationDatabase.MIGRATION_ARTICLE)
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
