package com.android.bjapplication.model

data class ArticleListResponse (val status:String,val message: String?,val totalResults:Int,val articles: List<Article>)