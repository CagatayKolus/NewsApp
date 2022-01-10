package com.cagataykolus.newsapp.model

data class News(
    var status: String?,
    var totalResults: Int?,
    var articles: List<Article>
)