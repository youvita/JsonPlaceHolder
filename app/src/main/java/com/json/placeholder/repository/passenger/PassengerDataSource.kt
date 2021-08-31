package com.json.placeholder.repository.passenger

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.json.placeholder.app.Constants.STARTING_PAGE_INDEX
import com.json.placeholder.data.PassengerItem
import com.json.placeholder.data.api.PassengerApi
import retrofit2.HttpException
import java.io.IOException

class PassengerDataSource(private val passengerApi: PassengerApi): PagingSource<Int, PassengerItem>()
{
    override fun getRefreshKey(state: PagingState<Int, PassengerItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PassengerItem> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = passengerApi.getPassenger(params.loadSize, page)
            val players = response.body()
            LoadResult.Page(
                data = players?.datas!!,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = page + 1
            )

        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
//    override fun getRequest(
//        loadSize: Int,
//        page: Int
//    ): RemoteDataSource<PagingResponse<PassengerItem>> {
//        return object : RemoteDataSource<PagingResponse<PassengerItem>>() {
//            override suspend fun createCall(): Response<PagingResponse<PassengerItem>> {
//                return passengerApi.getPassenger(loadSize, page)
//            }
//        }
//    }
}