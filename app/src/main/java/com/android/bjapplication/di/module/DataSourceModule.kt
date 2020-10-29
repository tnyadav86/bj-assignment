package com.android.bjapplication.di.module

import android.app.Application
import com.android.bjapplication.R
import com.android.bjapplication.model.database.AppDatabase
import com.android.bjapplication.model.database.dao.ArticleDao
import com.android.bjapplication.model.database.dao.SourceDao
import com.android.bjapplication.network.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataSourceModule {
    @Provides
    fun provideApiService(): ApiService {
        //TODO
        return Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideApiKey(application: Application): String{
        return  application.getString(R.string.api_key)

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