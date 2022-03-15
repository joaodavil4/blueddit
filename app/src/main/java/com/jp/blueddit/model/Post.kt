package com.jp.blueddit.model

import android.os.Parcelable


@Parcelize
data class Post(
    val objectId: String,
    val medias: String,
    val text: String,
    val postedBy: String,
    val createdAt: String,
): Parcelable