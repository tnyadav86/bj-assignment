package com.android.bjapplication.util

import androidx.lifecycle.LiveData

data class RepoResult< T : Any>(
    val data: LiveData<T>,
    val errors: LiveData<String?>
)