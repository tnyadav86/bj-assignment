package com.android.bjapplication.model.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.bjapplication.model.Article
import com.android.bjapplication.model.Source

@Dao
interface ArticleDao{

    @Query("SELECT * from Article")
    fun getArticle(): DataSource.Factory<Int, Article>

    @Query("SELECT * from Article WHERE  id = :id")
    fun getArticleBySource(id:String): DataSource.Factory<Int, Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articleList: List<Article>)


    @Query("DELETE FROM Article")
    fun deleteAll()
}