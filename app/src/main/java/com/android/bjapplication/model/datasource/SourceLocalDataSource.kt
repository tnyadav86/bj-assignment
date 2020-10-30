package com.android.bjapplication.model.datasource

import androidx.paging.DataSource
import com.android.bjapplication.model.Source
import com.android.bjapplication.model.database.dao.SourceDao
import javax.inject.Inject

class SourceLocalDataSource @Inject constructor(private val sourceDao: SourceDao){

    fun insertSource(sourceList: List<Source>) {
        sourceDao.insertAll(sourceList)
    }

    fun deleteSource() {
        sourceDao.deleteAll()
    }

     fun getSource(): List<Source> {
        return sourceDao.getSource()
    }
}