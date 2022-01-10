package com.cagataykolus.newsapp.ui.articles

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataykolus.newsapp.data.repository.ArticleRepository
import com.cagataykolus.newsapp.model.Article
import com.cagataykolus.newsapp.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
/**
 * ViewModel for [ArticlesFragment].
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class ArticlesViewModel @Inject constructor(private val articleRepository: ArticleRepository) : ViewModel() {
    private val _articlesLiveData = MutableLiveData<State<List<Article>>>()
    val articlesLiveData: LiveData<State<List<Article>>> = _articlesLiveData

    fun getArticles() {
        viewModelScope.launch {
            articleRepository.deleteAllArticles()
                .onStart {}
                .map {}
                .collect {}
        }

        val timer = object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                viewModelScope.launch {
                    articleRepository.getAllArticles()
                        .onStart { _articlesLiveData.value = State.loading() }
                        .map { resource -> State.fromResource(resource) }
                        .collect { state -> _articlesLiveData.value = state }
                }
            }
        }
        timer.start()
    }
}