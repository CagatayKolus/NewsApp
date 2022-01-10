package com.cagataykolus.newsapp.data.repository

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Failed<T>(val message: String) : Resource<T>()
}
