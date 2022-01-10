package com.cagataykolus.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */

@ExperimentalCoroutinesApi
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
