package com.jp.blueddit.ui.view.feed

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jp.blueddit.model.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FeedViewModel(override val coroutineContext: CoroutineContext) : ViewModel(), CoroutineScope {
    private val _posts = arrayListOf<Post>()

    var isRefreshing = MutableLiveData(false)
    var lazyListState: LazyListState? = null
    val posts = MutableLiveData<List<Post>>(listOf())
    //val feedScreenState = MutableLiveData(FeedListStates.NORMAL)
    var reachEnd = false
    var lockRequest = false
    var skip = 0

    fun loadItems(init: Boolean) {
        if (lockRequest) return
        launch {
            lockRequest = true
            if (_posts.isEmpty() && init) {
                loadInitialItems()
            } else {
                loadMore()
            }
            lockRequest = false
        }
    }

    private suspend fun loadInitialItems() {

        runCatching {

        }.onSuccess {

        }.onFailure {
            //TODO
        }
    }

    private suspend fun loadMore() {
        runCatching {

        }.onSuccess {
            if (it.isEmpty()) {
                reachEnd = true
            } else {
                addPosts(it)
            }
        }
        isRefreshing.value = false

    }

    fun refresh() {
        clearPostList()
        reachEnd = false
        isRefreshing.value = true
        loadItems(false)
    }

    private fun clearPostList() {
        skip = 0
        _posts.clear()
        posts.value = _posts.toList()
    }



}