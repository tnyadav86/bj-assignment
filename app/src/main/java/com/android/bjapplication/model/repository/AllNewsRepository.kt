package com.android.bjapplication.model.repository

import com.android.bjapplication.model.datasource.AllNewsLocalDataSource
import com.android.bjapplication.model.datasource.AllNewsRemoteDataSource
import javax.inject.Inject

class AllNewsRepository @Inject constructor(
    private val allNewsLocalDataSource: AllNewsLocalDataSource,
    private val allNewsRemoteDataSource: AllNewsRemoteDataSource
){


}