@file:Suppress("unused")

package com.cagataykolus.newsapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */

fun String.changeDateFormat(): String {
    return if (!this.isNullOrEmpty()) {
        val sourceSdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val requiredSdf = SimpleDateFormat("MM.dd.yyyy     HH:mm", Locale.getDefault())
        requiredSdf.format(sourceSdf.parse(this))
    } else {
        ""
    }
}

fun Any?.openNewTabWindow(urls: String, context : Context) {
    val uris = Uri.parse(urls)
    val intents = Intent(Intent.ACTION_VIEW, uris)
    val b = Bundle()
    b.putBoolean("new_window", true)
    intents.putExtras(b)
    context.startActivity(intents)
}
