package com.example.kaar.common.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


object BindingAdapter {
    @BindingAdapter("load_image_url")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl: String?) {
            imageView.loadUrl(imageUrl)
    }

    @BindingAdapter("formattedDate")
    @JvmStatic
    fun bindFormattedDate(textView: TextView, apiDateString: String?) {
        apiDateString?.let {
            val apiDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val apiDate: Date = apiDateFormat.parse(it)

            val desiredDateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
            val formattedDate = desiredDateFormat.format(apiDate)

                 textView.text = formattedDate
        }
    }
}

