package com.example.kaar.common.utils

import android.app.Activity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kaar.R



fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
    fun ImageView.loadUrl(imageUrl:String?){
        imageUrl?.let{
            Glide
                .with(this)
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.spinner_loading)
                .into(this);
        }
    }
//fun Activity.showToast(
//    title: String?,
//    description: String?,
//    style: MotionToastStyle
//) {
//    MotionToast.createColorToast(
//        this,
//        title,
//        description!!,
//        style,
//        MotionToast.GRAVITY_BOTTOM,
//        MotionToast.LONG_DURATION,
//        null
//    )
//}
