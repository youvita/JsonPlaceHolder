package com.source.module.network

import com.source.module.data.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
fun <ResultType, RequestType> networkBoundSource(
     query: () -> Flow<ResultType>,
     fetch: suspend () -> RequestType,
     saveFetchResult: suspend (RequestType) -> Unit,
     shouldFetch: (ResultType) -> Boolean = {true}
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it)}
        }catch (throwable: Throwable) {
            query().map { Resource.Error(throwable) }
        }
    } else {
        query().map { Resource.Success(it)
        }
    }

    emitAll(flow)
}