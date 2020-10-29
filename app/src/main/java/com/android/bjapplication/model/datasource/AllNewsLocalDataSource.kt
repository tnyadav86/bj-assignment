package com.android.bjapplication.model.datasource

import com.android.bjapplication.model.database.dao.ArticleDao
import javax.inject.Inject

class AllNewsLocalDataSource @Inject constructor(private val articleDao: ArticleDao)