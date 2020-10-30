package com.android.bjapplication.viewmodel

import androidx.lifecycle.*
import com.android.bjapplication.model.Source
import com.android.bjapplication.model.repository.SourceRepository
import com.android.bjapplication.util.RepoResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SourceFragmentViewModel @Inject constructor(private val sourceRepository: SourceRepository) :
    ViewModel() {

    private val userRepoResult = MutableLiveData<RepoResult<List<Source>>>()

    private val _errorStatusLiveData = userRepoResult.switchMap { it.errors}
    val errorStatusLiveData: LiveData<String?>
        get() = _errorStatusLiveData

    private val _sourceListLivedata = userRepoResult.switchMap { it.data }
    val sourceListLivedata: LiveData<List<Source>>
        get() = _sourceListLivedata

    fun fetchSource() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepoResult.postValue(sourceRepository.fetchSource())
        }

    }

}