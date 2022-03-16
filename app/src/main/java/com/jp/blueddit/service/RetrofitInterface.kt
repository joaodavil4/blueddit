package com.jp.blueddit.service

import com.jp.blueddit.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitInterface {

    @Headers("Content-Type: application/json")
    @GET(RetrofitConstants.URL)
    suspend fun getPosts(@Query("after") skip:Int,
                         @Query("limit") limit: Int) : Response<List<Post>>
}