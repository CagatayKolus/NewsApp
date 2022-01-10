package com.cagataykolus.newsapp.ui.articles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cagataykolus.newsapp.databinding.ItemArticleBinding
import com.cagataykolus.newsapp.model.Article
import com.cagataykolus.newsapp.ui.articles.viewholder.ArticlesViewHolder
import java.util.*

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
/**
 * Adapter class [RecyclerView.Adapter] for [RecyclerView] which binds [Article] along with [ArticlesViewHolder]
 * @param onItemClicked which will receive callback when item is clicked.
 */
class ArticlesAdapter(
    private val onItemClicked: (Article) -> Unit
) : ListAdapter<Article, ArticlesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticlesViewHolder(
        ItemArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.articleId == newItem.articleId

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem == newItem
        }
    }
}
