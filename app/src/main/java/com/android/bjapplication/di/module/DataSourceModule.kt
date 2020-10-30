package com.android.bjapplication.di.module

import android.app.Application
import com.android.bjapplication.BuildConfig
import com.android.bjapplication.R
import com.android.bjapplication.model.database.AppDatabase
import com.android.bjapplication.model.database.dao.ArticleDao
import com.android.bjapplication.model.database.dao.SourceDao
import com.android.bjapplication.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {

        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        if (BuildConfig.DEBUG) {
                            level = HttpLoggingInterceptor.Level.BODY
                        } else {
                            level = HttpLoggingInterceptor.Level.NONE
                        }
                    }).build()
            )
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideApiKey(application: Application): String {
        return application.getString(R.string.api_key)

    }

    @Provides
    fun provideSourceDao(application: Application): SourceDao {
        return AppDatabase.getInstance(application.applicationContext).sourceDao()
    }

    @Provides
    fun provideArticleDao(application: Application): ArticleDao {
        return AppDatabase.getInstance(application.applicationContext).articleDao()
    }

}