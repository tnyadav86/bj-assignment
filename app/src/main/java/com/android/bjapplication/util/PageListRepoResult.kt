package com.android.bjapplication.util

import androidx.lifecycle.LiveData
import com.android.bjapplication.network.TaskStatusResult

data class PageListRepoResult< T : Any>(
    val data: LiveData<T>,
    val taskStatus: LiveData<TaskStatusResult>
)