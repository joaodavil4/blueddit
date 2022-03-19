package com.jp.blueddit.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Post(
    val author: String,
    val id: String,
    val title: String,
    val thumbnail: String,
    val num_comments: Int,
    val created_utc: Int
): Parcelable