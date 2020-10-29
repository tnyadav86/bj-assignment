package com.android.bjapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Source(
    @PrimaryKey
    val id: String,
    val category: String?,
    val country: String,
    @ColumnInfo(name = "source_description")val description: String,
    val language: String,
    val name: String,
    @ColumnInfo(name = "url_description")val url: String
)