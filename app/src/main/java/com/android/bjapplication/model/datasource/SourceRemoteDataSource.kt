package com.android.bjapplication.model.datasource

import com.android.bjapplication.model.Source
import com.android.bjapplication.network.ApiService
import com.android.bjapplication.network.Constants.INTERNET_ERROR
import com.android.bjapplication.network.Constants.SERVER_ERROR
import com.android.bjapplication.network.DataResult
import com.android.bjapplication.util.isError
import com.android.bjapplication.util.isSuccess
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

class SourceRemoteDataSource @Inject constructor(
    private val service: ApiService,
    private val apiKey: String
) {
    suspend fun fetchSource(): DataResult<List<Source>> {

        try {
            val response = service.fetchSource(apiKey = apiKey).execute()
            return if (response.isSuccessful) {
                val responseBody = response.body()
                responseBody?.let {
                    if (it.isSuccess()) {
                        return DataResult.Success(it.sources)
                    } else if (it.isError()) {
                        return DataResult.Error(it.message)
                    } else {
                        return DataResult.Error(SERVER_ERROR)
                    }
                } ?: return DataResult.Error(SERVER_ERROR)

            } else {
                val jObjError = JSONObject(response.errorBody()!!.string())
                DataResult.Error(jObjError.getString("message"))
            }
        } catch (e: Exception) {
            if (e is IOException) {
                return DataResult.Error(INTERNET_ERROR)
            } else {
                return DataResult.Error(SERVER_ERROR)
            }

        }

    }
}