package com.android.bjapplication.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
   //TODO
    @GET("")
    fun fetchDetails(): Call<Any>



}
