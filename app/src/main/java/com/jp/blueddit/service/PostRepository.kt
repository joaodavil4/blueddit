package com.jp.blueddit.service

import com.jp.blueddit.model.Post

interface PostRepository {

    suspend fun getApiData(page: Int): List<Post> {
        return listOf()
    }
}