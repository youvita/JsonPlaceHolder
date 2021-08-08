package com.source.module.network

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.source.module.data.Resource
import com.source.module.rxjava.RxEvent
import com.source.module.rxjava.RxJava
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class RemoteDataSource<T> @MainThread constructor() {

    val result = MutableLiveData<Resource<T>>()

    private fun setValue(newValue: Resource<T>) {
        if (result.value != newValue) {
            result.postValue(newValue)
        }
    }

    suspend fun networkRequest() {
        setValue(Resource.Loading())

        withContext(Dispatchers.IO) {
            when (val response = RetrofitRequest.handleNetworkRequest { createCall() }) {
                is RetrofitResponse.Success -> {
                    Log.d("response:: ","Success ${response.body}")

                    val success = "Success Comment Post"
                    RxJava.publish(RxEvent.CommentSuccess(success))
                    setValue(Resource.Success(response.body))
                }
            }
        }
    }

    fun asLiveData() = result as LiveData<Resource<T>>

    @MainThread
    protected abstract suspend fun createCall(): Response<T>
}