package com.json.placeholder.data.api

import com.json.placeholder.app.Constants
import com.json.placeholder.data.PassengerItem
import com.source.module.data.PagingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface PassengerApi {

    @GET("${Constants.ApiVersion.API_MOBILE_VERSION}/passenger")
    suspend fun getPassenger(
        @Query("size") size: Int,
        @Query("page") page: Int
    ): Response<PagingResponse<PassengerItem>>
}