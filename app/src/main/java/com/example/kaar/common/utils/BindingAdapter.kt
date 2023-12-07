package com.example.kaar.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter


object BindingAdapter {
    @BindingAdapter("load_image_url")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl: String) {
            imageView.loadUrl(imageUrl)
    }
}

