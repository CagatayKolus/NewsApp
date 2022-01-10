package com.cagataykolus.newsapp.ui.articles

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cagataykolus.newsapp.R
import com.cagataykolus.newsapp.databinding.FragmentArticlesBinding
import com.cagataykolus.newsapp.model.Article
import com.cagataykolus.newsapp.model.State
import com.cagataykolus.newsapp.ui.articles.adapter.ArticlesAdapter
import com.cagataykolus.newsapp.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
/**
 * [ArticlesFragment] is fragment for listing [Article] data.
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ArticlesFragment : Fragment(R.layout.fragment_articles) {
    private val binding by viewBinding { FragmentArticlesBinding.bind(it) }
    private val viewModel by viewModels<ArticlesViewModel>()
    private val adapter = ArticlesAdapter(this::onItemClicked)

    override fun onStart() {
        super.onStart()

        observeArticles()
        handleNetworkChanges()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    /**
     * Observe data.
     */
    private fun observeArticles() {
        viewModel.articlesLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    showLoading(true)
                }
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        adapter.submitList(state.data.toMutableList())
                        showLoading(false)
                    }
                }
                is State.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    showLoading(false)
                }
            }
        }
    }

    private fun getArticles() = viewModel.getArticles()

    /**
     * Initialize recyclerview with values.
     */
    private fun initView() {
        binding.run {
            recyclerviewArticlesList.adapter = adapter
            swiperefreshlayoutArticlesRefresh.setOnRefreshListener { getArticles() }
        }

        viewModel.articlesLiveData.value?.let { currentState ->
            if (!currentState.isSuccessful()) {
                getArticles()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.swiperefreshlayoutArticlesRefresh.isRefreshing = isLoading
    }

    /**
     * If click any item, navigate to detail fragment.
     */
    private fun onItemClicked(article: Article) {
           findNavController().navigate(
            R.id.action_articlesFragment_to_detailFragment,
            bundleOf(
                "DETAIL" to article
            )
        )
    }

    /**
     * Observes network changes.
     */
    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(requireContext()).observe(this) { isConnected ->
            if (!isConnected) {
                binding.textviewArticlesNetworkStatus.text =
                    getString(R.string.internet_connectivity_fail)
                binding.linearlayoutArticlesNetworkStatus.apply {
                    show()
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.connectivity_fail
                        )
                    )
                }
            } else {
                if (viewModel.articlesLiveData.value is State.Error || adapter.itemCount == 0) {
                    getArticles()
                }
                binding.textviewArticlesNetworkStatus.text =
                    getString(R.string.internet_connectivity_success)
                binding.linearlayoutArticlesNetworkStatus.apply {
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.connectivity_success
                        )
                    )

                    animate()
                        .alpha(1f)
                        .setStartDelay(1000L)
                        .setDuration(1000L)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        }
    }
}