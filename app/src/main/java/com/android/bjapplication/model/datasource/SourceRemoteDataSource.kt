package com.android.bjapplication.model.datasource

import com.android.bjapplication.network.ApiService
import javax.inject.Inject

class SourceRemoteDataSource @Inject constructor(
    private val service: ApiService,
    private val apiKey: String
)