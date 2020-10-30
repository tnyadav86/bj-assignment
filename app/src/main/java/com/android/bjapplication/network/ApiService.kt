package com.android.bjapplication.network

import com.android.bjapplication.model.ArticleListResponse
import com.android.bjapplication.model.SourceListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
   //TODO
    @GET("v2/top-headlines")
    fun fetchHeadLines(
       @Query("language") language: String = "en",
       @Query("page") page: String,
       @Query("pageSize") pageSize: String,
       @Query("sources") sources: String,
       @Query("apiKey") apiKey: String
   ): Call<ArticleListResponse>

    @GET("v2/sources")
    fun fetchSource(
        @Query("language") language: String = "en",
        @Query("category") category: String = "technology",
        @Query("apiKey") apiKey: String
    ): Call<SourceListResponse>

    @GET("v2/everything")
    fun fetchArticles(
        @Query("language") sources: String="en",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("q") q: String = "India",
        @Query("page") page: String,
        @Query("pageSize") pageSize: String,
        @Query("apiKey") apiKey: String
    ): Call<ArticleListResponse>

}
