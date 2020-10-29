package com.android.bjapplication.model.datasource

import com.android.bjapplication.model.database.dao.SourceDao
import javax.inject.Inject

class SourceLocalDataSource @Inject constructor(private val sourceDao: SourceDao)