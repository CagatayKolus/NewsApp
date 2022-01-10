package com.cagataykolus.newsapp.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cagataykolus.newsapp.databinding.ActivityMainBinding
import com.cagataykolus.newsapp.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Çağatay Kölüş on 10.01.2022.
 * cagataykolus@gmail.com
 */
/**
 * [MainActivity] contains fragments.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding { ActivityMainBinding.inflate(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}