package com.android.bjapplication.network

sealed class DataResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : DataResult<T>()
    data class Error(val errorMessage: String?) : DataResult<Nothing>()

}
