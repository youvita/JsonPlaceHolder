package com.source.module.network

sealed class Resource<out T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<out T>(data: T) : Resource<T>(data)
    class Loading<out T>(data: T? = null) : Resource<T>(data)
    class Error<out T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
}