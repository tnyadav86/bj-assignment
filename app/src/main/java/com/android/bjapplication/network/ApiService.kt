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
       @Query("country") country: String = "in",
       @Query("language") language: String = "en",
       @Query("language") sources: String,
       @Query("apiKey") apiKey: String
   ): Call<ArticleListResponse>

    @GET("v2/sources")
    fun fetchSource(
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String
    ): Call<SourceListResponse>

    @GET("v2/everything")
    fun fetchArticles(
        @Query("language") sources: String="en",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String
    ): Call<ArticleListResponse>

}
