package com.jp.blueddit.service

import com.jp.blueddit.model.Post

class RedditNewsResponse(val data: RedditDataResponse)

class RedditDataResponse(
    val children: List<RedditChildrenResponse>,
    val after: String?,
    val before: String?
)

class RedditChildrenResponse(val data: Post)
