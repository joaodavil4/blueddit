package com.jp.blueddit.service

import com.jp.blueddit.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostRepositoryClass : PostRepository {

    override suspend fun getApiData(page: Int): List<Post> {
        return withContext(Dispatchers.Default) {
            val retrofitClient = RetrofitUtils
                .getRetrofitInstance(RetrofitConstants.URL)
            val endpoint = retrofitClient.create(RetrofitInterface::class.java)
            endpoint.getPosts(page, 10)
        }
    }
}