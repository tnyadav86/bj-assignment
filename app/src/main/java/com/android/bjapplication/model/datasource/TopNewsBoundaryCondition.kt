package com.android.bjapplication.model.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.android.bjapplication.model.Article
import com.android.bjapplication.network.Constants
import com.android.bjapplication.network.Constants.PAGE_SIZE
import com.android.bjapplication.network.TaskStatusResult

class TopNewsBoundaryCondition(
    private val topNewsRemoteDataSource: TopNewsRemoteDataSource,
    private val topNewsLocalDataSource: TopNewsLocalDataSource,
    private val source: String
) : PagedList.BoundaryCallback<Article>() {

    private var lastRequestedPage = Constants.INITIAL_PAGE
    private var totalPages = 0

    val _taskStatusLiveData = MutableLiveData<TaskStatusResult>()

    // LiveData of network errors.
    val taskStatusLiveData: LiveData<TaskStatusResult>
        get() = _taskStatusLiveData


    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        if (isRequestInProgress) return
        isRequestInProgress = true
        _taskStatusLiveData.postValue(TaskStatusResult.Loading())
        topNewsRemoteDataSource.getAllNewsAPI(
            lastRequestedPage.toString(),
            PAGE_SIZE.toString(),source,
            {
                totalPages = it.totalResults/PAGE_SIZE
                topNewsLocalDataSource.insertArticle(it.articles)
                _taskStatusLiveData.postValue(TaskStatusResult.Success())
                isRequestInProgress = false

            },
            {
                isRequestInProgress = false
                _taskStatusLiveData.postValue(TaskStatusResult.Error(it))
            })

    }

    override fun onItemAtEndLoaded(itemAtEnd: Article) {
        if (lastRequestedPage == totalPages) return
        if (isRequestInProgress) return
        isRequestInProgress = true
        lastRequestedPage++
        topNewsRemoteDataSource.getAllNewsAPI(
            lastRequestedPage.toString(),
            PAGE_SIZE.toString(),source,
            {
                topNewsLocalDataSource.insertArticle(it.articles)
                isRequestInProgress = false
                _taskStatusLiveData.postValue(TaskStatusResult.Success())

            },
            {
                isRequestInProgress = false
                _taskStatusLiveData.postValue(TaskStatusResult.Error(it))
            })
    }

}
