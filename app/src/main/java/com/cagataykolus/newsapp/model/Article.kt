package com.cagataykolus.newsapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cagataykolus.newsapp.model.Article.Companion.TABLE_ARTICLE
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
@Entity(tableName = TABLE_ARTICLE)
@TypeConverters(
    SourceConverter::class
)
data class Article(
    var source:  @RawValue Source?,
    var author: String?,
    var title: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: String?,
    var content: String?
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var articleId: Int = 0

    companion object {
        const val TABLE_ARTICLE = "table_article"
    }
}