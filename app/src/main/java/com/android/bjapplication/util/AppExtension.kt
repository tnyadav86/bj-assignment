package com.android.bjapplication.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.bjapplication.R
import com.android.bjapplication.model.SourceListResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun SourceListResponse.isSuccess():Boolean{
    return status=="ok"
}

fun SourceListResponse.isError():Boolean{
    return status=="error"
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.loadImage(url: String?) {

    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.setColorSchemeColors(R.attr.colorPrimary)
    circularProgressDrawable.start()

    Glide.with(context)
        .load(url)
        .centerCrop()
        .error(R.mipmap.ic_launcher)
        .placeholder(circularProgressDrawable)
        .apply(
            RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        )
        .into(this)


}
val Context.isNetworkConnected: Boolean
    get() = (getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.activeNetworkInfo?.isConnected == true
