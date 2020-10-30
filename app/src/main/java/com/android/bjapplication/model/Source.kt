package com.android.bjapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Source(
    @PrimaryKey
    val id: String,
    val category: String?,
    val country: String?,
    val description: String?,
    val language: String?,
    val name: String,
    val url: String?
)