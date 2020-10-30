package com.android.bjapplication.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.bjapplication.model.Source

@Dao
interface SourceDao {

    @Query("SELECT * from Source")
    fun getSource(): List<Source>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(sourceList: List<Source>)


    @Query("DELETE FROM Source")
    fun deleteAll()
}