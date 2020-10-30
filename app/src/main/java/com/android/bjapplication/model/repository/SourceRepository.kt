package com.android.bjapplication.model.repository

import androidx.lifecycle.MutableLiveData
import com.android.bjapplication.model.Source
import com.android.bjapplication.model.datasource.SourceLocalDataSource
import com.android.bjapplication.model.datasource.SourceRemoteDataSource
import com.android.bjapplication.network.DataResult
import com.android.bjapplication.util.RepoResult
import javax.inject.Inject

class SourceRepository @Inject constructor(
    private val sourceLocalDataSource: SourceLocalDataSource,
    private val sourceRemoteDataSource: SourceRemoteDataSource
) {

    suspend fun fetchSource(): RepoResult<List<Source>> {
        val errors = MutableLiveData<String>().apply {
            postValue(null)
        }
        val data = MutableLiveData<List<Source>>()
        var localData = sourceLocalDataSource.getSource()
        data.postValue(localData)
        val remoteData = sourceRemoteDataSource.fetchSource()
        when (remoteData) {
            is DataResult.Success -> {
                sourceLocalDataSource.insertSource(remoteData.data)
                localData = sourceLocalDataSource.getSource()
                data.postValue(localData)
                errors.postValue(null)

            }
            is DataResult.Error -> {
                errors.postValue(remoteData.errorMessage)
            }

        }
        return RepoResult(data, errors)
    }

}