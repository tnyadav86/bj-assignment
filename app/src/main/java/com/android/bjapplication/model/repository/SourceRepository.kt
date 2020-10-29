package com.android.bjapplication.model.repository

import com.android.bjapplication.model.datasource.SourceLocalDataSource
import com.android.bjapplication.model.datasource.SourceRemoteDataSource
import javax.inject.Inject

class SourceRepository @Inject constructor(
    private val sourceLocalDataSource: SourceLocalDataSource,
    private val sourceRemoteDataSource: SourceRemoteDataSource
)