package com.json.placeholder.data.api

import com.json.placeholder.app.Constants
import com.json.placeholder.data.CommentsItem
import com.json.placeholder.data.PassengerItem
import com.json.placeholder.data.PostItem
import com.source.module.data.PagingResponse
import com.source.module.data.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface CommentApi {

    @GET("posts/{postId}/comments")
    suspend fun getComments(@Path("postId") postId: Int) : List<CommentsItem>

    @GET("posts/1")
    suspend fun getPosts() : Response<PostItem>

    @GET("${Constants.ApiVersion.API_MOBILE_VERSION}/airlines")
    suspend fun getPassengerItem(): Response<PassengerItem>
}