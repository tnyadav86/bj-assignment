package com.android.bjapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.android.bjapplication.model.Article
import com.android.bjapplication.model.repository.AllNewsRepository
import com.android.bjapplication.util.PageListRepoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllNewsFragmentViewModel @Inject constructor(private val allNewsRepository :AllNewsRepository): ViewModel() {
    private val allNewsRepoResult = MutableLiveData<PageListRepoResult<PagedList<Article>>>()

    val articleListLivedata = allNewsRepoResult.switchMap {
        it.data
    }
    val taskStatusLiveData = allNewsRepoResult.switchMap {
        it.taskStatus
    }

    fun getArticle() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = allNewsRepository.getArticle()
            allNewsRepoResult.postValue(data)
        }

    }

    fun refreshArticle() = allNewsRepository.refreshArticle()
}