package com.android.bjapplication.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.bjapplication.model.database.ArticleSource

@Entity(primaryKeys = ["name","title"])
data class Article(
    val title: String,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val url: String?,
    val urlToImage: String?,
    @Embedded val source: ArticleSource
)