package com.android.bjapplication.model.datasource

import androidx.paging.DataSource
import com.android.bjapplication.model.Article
import com.android.bjapplication.model.database.dao.ArticleDao
import javax.inject.Inject

class AllNewsLocalDataSource @Inject constructor(private val articleDao: ArticleDao){

    fun insertArticle(articleList: List<Article>) {
        articleDao.insertAll(articleList)
    }

    fun deleteArticle() {
        articleDao.deleteAll()
    }

    suspend fun getArticle(): DataSource.Factory<Int, Article> {
        return articleDao.getArticle()
    }
}