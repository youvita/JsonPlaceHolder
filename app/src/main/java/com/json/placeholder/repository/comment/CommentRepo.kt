package com.json.placeholder.repository.comment

import androidx.room.withTransaction
import com.json.placeholder.data.CommentsItem
import com.json.placeholder.data.api.CommentApi
import com.json.placeholder.data.db.AppDataBase
import com.source.module.data.Resource
import com.source.module.network.RemoteDataSource
import com.source.module.network.networkBoundSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class CommentRepo(private val api: CommentApi, private val db: AppDataBase) {

    private val commentDao = db.commentDao()

//    fun getComments(): Flow<Resource<List<CommentsItem>>> = flow {
//        delay(1000)
//        try {
//            val request = object: RemoteDataSource<List<CommentsItem>>() {
//                override suspend fun createCall(): Response<List<CommentsItem>> {
//                    return api.getComments(1)
//                }
//            }
//            request.networkRequest()
//            emit(request.asLiveData().value!!)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

    @ExperimentalCoroutinesApi
    fun getCommentBound() = networkBoundSource(
        query = {
            commentDao.getAllComments()
        },
        fetch = {
            delay(2000)
            api.getComments(1)
//            delay(1000)
//            try {
//                val request = object: RemoteDataSource<List<CommentsItem>>() {
//                    override suspend fun createCall(): Response<List<CommentsItem>> {
//                        return api.getComments(1)
//                    }
//                }
//                request.networkRequest()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
        },
        saveFetchResult = { comment ->
            db.withTransaction {
                commentDao.deleteAllComments()
                commentDao.insertComments(comment)
            }
        }
    )

//        fun loadPassenger(): Flow<List<CommentsItem>> = flow {
//        delay(1000)
//        try {
//            val request = object: RemoteDataSource<List<CommentsItem>>() {
//                override suspend fun createCall(): Response<List<CommentsItem>> {
//                    return api.getComments(1)
//                }
//            }
//            request.networkRequest()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
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
//    }

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