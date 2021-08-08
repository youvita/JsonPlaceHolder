package com.app.source.module.base

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.source.module.data.PagingResponse
import com.source.module.data.Resource
import com.source.module.network.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BasePagingDataSource<T>(private val scope: CoroutineScope) : PageKeyedDataSource<Int, T>() {

    var networkState = MutableLiveData<Resource<PagingResponse<T>>>()
    var retry: (() -> Unit)? = null

    abstract fun getRequest(loadSize: Int, page: Int = 1): RemoteDataSource<PagingResponse<T>>

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {
        loadDataItems(0, getRequest(params.requestedLoadSize)) { dataItems, nextPage ->
            callback.onResult(dataItems, 1, nextPage)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        loadDataItems(
            params.key,
            getRequest(params.key)
        ) { dataItems, nextPage ->
            callback.onResult(dataItems, nextPage)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {}

    open fun loadDataItems(currentOffset: Int,
                      request: RemoteDataSource<PagingResponse<T>>,
                      completion: ((dataItems: MutableList<T>, nextPage: Int) -> Unit)) {

        scope.launch(Dispatchers.Main) {
            request.result.observeForever {
                networkState.postValue(it)
            }
        }

        scope.launch {
            request.networkRequest()
            request.asLiveData().observeForever{
                if (it is Resource.Success) {
                    val data = it.data ?: return@observeForever
                    val nextPage = data.pages?.plus(1) ?: return@observeForever
                    val dataItems = data.datas ?: return@observeForever
                    completion.invoke(dataItems, nextPage)
                } else if (it is Resource.Error) {
                    retry = {
                        scope.launch {
                            request.networkRequest()
                        }
                    }
                }
            }
        }
    }

    fun retryDataItem() {
        retry?.invoke()
    }
}