package com.cagataykolus.newsapp.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun Fragment.isConnectedToInternet() : Boolean{
    return NetworkUtils.getNetworkLiveData(requireContext()).value == true
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.context, message, duration).show()
}