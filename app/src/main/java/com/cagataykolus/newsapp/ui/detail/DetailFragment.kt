package com.cagataykolus.newsapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.cagataykolus.newsapp.R
import com.cagataykolus.newsapp.databinding.FragmentDetailBinding
import com.cagataykolus.newsapp.model.Article
import com.cagataykolus.newsapp.ui.articles.ArticlesFragment
import com.cagataykolus.newsapp.ui.articles.ArticlesViewModel
import com.cagataykolus.newsapp.utils.isConnectedToInternet
import com.cagataykolus.newsapp.utils.openNewTabWindow
import com.cagataykolus.newsapp.utils.showToast
import com.cagataykolus.newsapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
/**
 * [DetailFragment] is fragment for [Article] details.
 */
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding { FragmentDetailBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = arguments?.getParcelable<Article>("DETAIL")
        content?.let {
            showDetails(it)
        }
    }

    private fun showDetails(article: Article) {
        binding.textviewDetailSource.text = article.source?.name
        binding.textviewDetailAuthor.text = article.author
        binding.textviewDetailTitle.text = article.title
        binding.textviewDetailDescription.text = article.description
        binding.imageviewDetailPhoto.load(article.urlToImage) {
            placeholder(R.drawable.image_placeholder)
            error(R.drawable.image_broken)
        }
        binding.fabDetailLink.setOnClickListener {
            article.url?.let { url -> openNewTabWindow(url, requireContext()) }
        }
    }
}