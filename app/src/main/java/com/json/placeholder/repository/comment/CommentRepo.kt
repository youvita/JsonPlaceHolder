package com.json.placeholder.repository.comment

import com.json.placeholder.data.CommentsItem
import com.json.placeholder.data.api.CommentApi
import com.source.module.data.Resource
import com.source.module.network.RemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class CommentRepo(private val commentApi: CommentApi) {

    fun getComments(): Flow<Resource<List<CommentsItem>>> = flow {
        delay(1000)
        try {
            val request = object: RemoteDataSource<List<CommentsItem>>() {
                override suspend fun createCall(): Response<List<CommentsItem>> {
                    return commentApi.getComments(1)
                }
            }
            request.networkRequest()
            emit(request.asLiveData().value!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

        fun loadPassenger(): Flow<List<CommentsItem>> = flow {
        delay(1000)
        try {
            val request = object: RemoteDataSource<List<CommentsItem>>() {
                override suspend fun createCall(): Response<List<CommentsItem>> {
                    return commentApi.getComments(1)
                }
            }
            request.networkRequest()
        } catch (e: Exception) {
            e.printStackTrace()
        }
//        scope: CoroutineScope
//    ): Listing<PassengerItem> {
//        val sourceFactory = PassengerDataSourceFactory(scope, commentApi)
//
//        val livePagedList: LiveData<PagedList<PassengerItem>> =
//            LivePagedListBuilder(sourceFactory, pageListConfig).build()
//
//        val networkState = Transformations.switchMap(sourceFactory.sourceLiveData) { it.networkState }
//
//        return Listing(
//            pagedList = livePagedList,
//            networkState = networkState,
//            retry = { sourceFactory.sourceLiveData.value?.retryDataItem() },
//            refresh = { sourceFactory.sourceLiveData.value?.invalidate() }
//        )
    }

//    fun getPassenger(): Flow<PagingResponse<PassengerItem>> = flow {
//        delay(1000)
//        try {
//            val request = object: RemoteDataSource<PagingResponse<PassengerItem>>() {
//                override suspend fun createCall(): Response<PagingResponse<PassengerItem>> {
//                    return passengerApi.getPassenger(10,1)
//                }
//            }
//            request.networkRequest()
//            emit(request.asLiveData().value?.data!!)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
}