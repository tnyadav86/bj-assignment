package com.android.bjapplication.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class ArticleSource(
    val id: String?,
    val name: String
)