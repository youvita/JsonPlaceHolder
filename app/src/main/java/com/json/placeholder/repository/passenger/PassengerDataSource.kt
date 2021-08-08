package com.json.placeholder.repository.passenger

import com.app.source.module.base.BasePagingDataSource
import com.json.placeholder.data.PassengerItem
import com.json.placeholder.data.api.PassengerApi
import com.source.module.data.PagingResponse
import com.source.module.network.RemoteDataSource
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response
import javax.inject.Inject

class PassengerDataSource(
    scope: CoroutineScope,
    private val passengerApi: PassengerApi): BasePagingDataSource<PassengerItem>(scope)
{
    override fun getRequest(
        loadSize: Int,
        page: Int
    ): RemoteDataSource<PagingResponse<PassengerItem>> {
        return object : RemoteDataSource<PagingResponse<PassengerItem>>() {
            override suspend fun createCall(): Response<PagingResponse<PassengerItem>> {
                return passengerApi.getPassenger(loadSize, page)
            }
        }
    }
}