package com.json.placeholder.repository.passenger

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.*
import com.json.placeholder.data.PassengerItem
import com.json.placeholder.data.api.PassengerApi
import com.source.module.data.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PassengerRepo @Inject constructor(private val passengerApi: PassengerApi) {

//    fun loadPassenger(scope: CoroutineScope): Listing<PassengerItem> {
//        val sourceFactory = PassengerDataSourceFactory(scope, passengerApi)
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
//    }

//    fun getComments(): Flow<Resource<List<CommentsItem>>> = flow {
////        delay(1000)
////        try {
////            val request = object: RemoteDataSource<List<CommentsItem>>() {
////                override suspend fun createCall(): Response<List<CommentsItem>> {
////                    return api.getComments(1)
////                }
////            }
////            request.networkRequest()
////            emit(request.asLiveData().value!!)
////        } catch (e: Exception) {
////            e.printStackTrace()
////        }
////    }


    fun getPlayers(): Flow<PagingData<PassengerItem>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                PassengerDataSource(passengerApi)
            }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}