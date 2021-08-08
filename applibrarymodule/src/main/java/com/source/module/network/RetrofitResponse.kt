package com.source.module.network

sealed class RetrofitResponse<out T> {

    data class Success<T>(val body: T) : RetrofitResponse<T>()

    data class Exception<T>(val throwable: Throwable) : RetrofitResponse<T>()

    data class Error<T>(val code: Int, val msg: String? = null,val errorBody : T ?= null) : RetrofitResponse<T>()
}