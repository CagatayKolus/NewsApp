package com.cagataykolus.newsapp.ui.articles.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cagataykolus.newsapp.R
import com.cagataykolus.newsapp.databinding.ItemArticleBinding
import com.cagataykolus.newsapp.model.Article
import com.cagataykolus.newsapp.utils.changeDateFormat
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
/**
 * [RecyclerView.ViewHolder] implementation to inflate View for RecyclerView.
 */
class ArticlesViewHolder(private val binding: ItemArticleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, onItemClicked: (Article) -> Unit) {
        binding.imageviewArticlePhoto.load(article.urlToImage) {
            placeholder(R.drawable.image_placeholder)
            error(R.drawable.image_broken)
        }
        binding.textviewArticleTitle.text = article.title
        binding.textviewArticleDate.text = article.publishedAt?.changeDateFormat()

        binding.root.setOnClickListener {
            onItemClicked(article)
        }
    }
}