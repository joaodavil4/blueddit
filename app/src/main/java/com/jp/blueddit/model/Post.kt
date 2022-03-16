package com.jp.blueddit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Post(
    val author: String,
    val id: String,
    val title: String,
    val thumbnail: String,
    val num_comments: Int,
    val postedBy: String,
    val created_utc: Int
): Parcelable