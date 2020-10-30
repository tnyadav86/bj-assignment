package com.android.bjapplication.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.android.bjapplication.model.Article
import com.android.bjapplication.model.datasource.*
import com.android.bjapplication.network.Constants.INITIAL_PAGE
import com.android.bjapplication.network.Constants.PAGE_SIZE
import com.android.bjapplication.network.Constants.PREFETCH_DISTANCE
import com.android.bjapplication.network.TaskStatusResult
import com.android.bjapplication.util.PageListRepoResult
import javax.inject.Inject

class TopNewsRepository @Inject constructor(
    private val topNewsLocalDataSource: TopNewsLocalDataSource,
    private val topNewsRemoteDataSource: TopNewsRemoteDataSource
){
    private val pageListConfig by lazy {
        PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .setEnablePlaceholders(false)
            .build()
    }

    suspend fun getArticle(source:String): PageListRepoResult<PagedList<Article>> {

        val dataSourceFactory = topNewsLocalDataSource.getArticle(source)
        // Construct the boundary callback
        val boundaryCallback =
            TopNewsBoundaryCondition(topNewsRemoteDataSource, topNewsLocalDataSource,source)
        val taskStatusLiveData = boundaryCallback.taskStatusLiveData

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, pageListConfig)
            .setBoundaryCallback(boundaryCallback)
            .build()

        // Get data and network errorsfrom boundary callback
        return PageListRepoResult(data, taskStatusLiveData)
    }

    fun refreshArticle(source:String): LiveData<TaskStatusResult> {
        val data = MutableLiveData<TaskStatusResult>()
        topNewsRemoteDataSource.getAllNewsAPI(
            INITIAL_PAGE.toString(),
            PAGE_SIZE.toString(),
            source,
            {
                if (it.articles.isNotEmpty()){
                    topNewsLocalDataSource.deleteArticle()
                    topNewsLocalDataSource.insertArticle(it.articles)
                    data.postValue(TaskStatusResult.Success())

                }else{
                    data.postValue(TaskStatusResult.Error())
                }

            },
            {
                data.postValue(TaskStatusResult.Error(it))
            })
        return data
    }

}