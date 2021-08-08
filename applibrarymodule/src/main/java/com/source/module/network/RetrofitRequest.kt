package com.source.module.network

import retrofit2.Response

object RetrofitRequest {

    suspend fun <T> handleNetworkRequest(call: suspend () -> Response<T>) : RetrofitResponse<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body == null) {
                    RetrofitResponse.Error(response.code())
                } else {
                    RetrofitResponse.Success(body)
                }
            } else {
                val errorBody: T? = null
                RetrofitResponse.Error(response.code(), response.message(), errorBody)
            }
        } catch (throwable: Throwable) {
            RetrofitResponse.Exception(throwable)
        }
    }

}