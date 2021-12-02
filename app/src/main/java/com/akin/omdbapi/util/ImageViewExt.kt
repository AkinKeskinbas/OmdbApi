package com.akin.omdbapi.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.akin.omdbapi.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.loadString(imgUrl: String?, placeholder: CircularProgressDrawable) {
    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(imgUrl)
        .centerCrop()
        .into(this)

}


fun makePlaceHolder(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }

}

fun String.makeBigger(): String {
    val biggerImage =
        this.replace("SX300.jpg", "SX600.jpg")
    return biggerImage.toString()
}