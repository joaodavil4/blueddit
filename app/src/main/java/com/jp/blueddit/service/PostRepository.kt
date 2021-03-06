package com.jp.blueddit.service

import com.jp.blueddit.model.Post
import retrofit2.Response

interface PostRepository {

    suspend fun getApiData(page: Int): Response<RedditNewsResponse> {
        return Response.success(null)
    }
}