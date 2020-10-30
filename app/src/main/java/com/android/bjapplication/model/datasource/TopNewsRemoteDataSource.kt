package com.android.bjapplication.model.datasource

import com.android.bjapplication.model.ArticleListResponse
import com.android.bjapplication.network.ApiService
import com.android.bjapplication.network.Constants
import com.android.bjapplication.util.isError
import com.android.bjapplication.util.isSuccess
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class TopNewsRemoteDataSource @Inject constructor(
    private val service: ApiService,
    private val apiKey: String
) {

    fun getAllNewsAPI(
        page: String, perPage: String,source:String,
        onSuccess: (articleListResponse: ArticleListResponse) -> Unit,
        onError: (error: String) -> Unit
    ) {

        val call = service.fetchHeadLines(page = page, pageSize = perPage,sources = source, apiKey = apiKey)

        call.enqueue(object : retrofit2.Callback<ArticleListResponse> {
            override fun onFailure(call: Call<ArticleListResponse>, t: Throwable) {
                if (t is IOException) {
                    onError(Constants.INTERNET_ERROR)
                } else {
                    onError(Constants.SERVER_ERROR)
                }
            }

            override fun onResponse(
                call: Call<ArticleListResponse>,
                response: Response<ArticleListResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let {
                        if (it.isSuccess()) {
                            onSuccess(it)
                        } else if (it.isError()) {
                            onError(it.message ?: Constants.SERVER_ERROR)
                        } else {
                            onError(Constants.SERVER_ERROR)
                        }
                    } ?: onError(Constants.SERVER_ERROR)

                } else {
                    val jObjError = JSONObject(response.errorBody()!!.string())
                    onError(jObjError.getString("message"))
                }
            }

        })


    }

}