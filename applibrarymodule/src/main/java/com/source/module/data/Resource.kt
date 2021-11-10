package com.source.module.data

import com.source.module.data.Status.*

sealed class Resource<T>(
   val status: Status? = null,
   val data: T? = null,
   val error: Throwable? = null
) {

   class Success<T>(data: T? = null) : Resource<T>(SUCCESS, data)
   class Loading<T>(data: T? = null) : Resource<T>(LOADING, data)
   class Error<T>(data: T? = null, throwable: Throwable) : Resource<T>(ERROR, data, throwable)
}