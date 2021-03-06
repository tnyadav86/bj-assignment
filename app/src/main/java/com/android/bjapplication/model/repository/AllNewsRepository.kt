package com.android.bjapplication.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.android.bjapplication.model.Article
import com.android.bjapplication.model.datasource.AllNewsBoundaryCondition
import com.android.bjapplication.model.datasource.AllNewsLocalDataSource
import com.android.bjapplication.model.datasource.AllNewsRemoteDataSource
import com.android.bjapplication.network.Constants.INITIAL_PAGE
import com.android.bjapplication.network.Constants.PAGE_SIZE
import com.android.bjapplication.network.Constants.PREFETCH_DISTANCE
import com.android.bjapplication.network.TaskStatusResult
import com.android.bjapplication.util.PageListRepoResult
import com.android.bjapplication.util.RepoResult
import javax.inject.Inject

class AllNewsRepository @Inject constructor(
    private val allNewsLocalDataSource: AllNewsLocalDataSource,
    private val allNewsRemoteDataSource: AllNewsRemoteDataSource
){
    private val pageListConfig by lazy {
        PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setPrefetchDistance(PREFETCH_DISTANCE)
            .setEnablePlaceholders(false)
            .build()
    }

    suspend fun getArticle(): PageListRepoResult<PagedList<Article>> {

        val dataSourceFactory = allNewsLocalDataSource.getArticle()
        // Construct the boundary callback
        val boundaryCallback =
            AllNewsBoundaryCondition(allNewsRemoteDataSource, allNewsLocalDataSource)
        val taskStatusLiveData = boundaryCallback.taskStatusLiveData

        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, pageListConfig)
            .setBoundaryCallback(boundaryCallback)
            .build()

        // Get data and network errorsfrom boundary callback
        return PageListRepoResult(data, taskStatusLiveData)
    }

    fun refreshArticle(): LiveData<TaskStatusResult> {
        val data = MutableLiveData<TaskStatusResult>()
        allNewsRemoteDataSource.getAllNewsAPI(
            INITIAL_PAGE.toString(),
            PAGE_SIZE.toString(),
            {
                if (it.articles.isNotEmpty()){
                    allNewsLocalDataSource.deleteArticle()
                    allNewsLocalDataSource.insertArticle(it.articles)
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