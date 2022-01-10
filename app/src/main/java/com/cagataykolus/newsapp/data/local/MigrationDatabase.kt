package com.cagataykolus.newsapp.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cagataykolus.newsapp.model.Article

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
/**
 * Each Migration has a start and end versions and Room runs these migrations to bring the
 * database to the latest version. The migration object that can modify the database and
 * to the necessary changes.
 */
object MigrationDatabase {
    const val DB_VERSION = 2

    val MIGRATION_ARTICLE: Array<Migration>
        get() = arrayOf(
            migrationArticle()
        )

    private fun migrationArticle(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${Article.TABLE_ARTICLE} ADD COLUMN body TEXT")
        }
    }
}

