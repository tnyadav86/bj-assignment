package com.android.bjapplication.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.bjapplication.R
import com.android.bjapplication.model.ArticleListResponse
import com.android.bjapplication.model.SourceListResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import org.joda.time.*
import java.text.SimpleDateFormat

fun SourceListResponse.isSuccess(): Boolean {
    return status == "ok"
}

fun SourceListResponse.isError(): Boolean {
    return status == "error"
}

fun ArticleListResponse.isSuccess(): Boolean {
    return status == "ok"
}

fun ArticleListResponse.isError(): Boolean {
    return status == "error"
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

fun String.getAge(currentDate : Long?=System.currentTimeMillis()): String {
    var age = this
    try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        val date = inputFormat.parse(this)
        val longTime = date.time
        val dt1 = Instant(longTime)
        val dt2 = Instant(currentDate)

        val thours = Hours.hoursBetween(dt1, dt2).hours
        val tminutes = Minutes.minutesBetween(dt1, dt2).minutes
        val tseconds = Seconds.secondsBetween(dt1, dt2).seconds
        val tdays = Days.daysBetween(dt1, dt2).days

        val hours = thours % 24
        val minutes = tminutes % 60
        val seconds = tseconds % 60

        if (tdays > 0) {
            if (tdays === 1) {
                age = "$tdays day ago"
            } else {
                age = "$tdays days ago"
            }
            return age
        } else if (hours > 0) {
            if (hours === 1) {
                age = "$hours hour ago"
            } else {
                age = "$hours hours ago"
            }
            return age
        } else if (minutes > 0) {
            if (minutes === 1) {
                age = "$minutes minute ago"
            } else {
                age = "$minutes minutes ago"
            }
            return age
        } else if (seconds > 0) {
            age = "Just Now"

        }
    } catch (e: Exception) {
    }
    return age
}