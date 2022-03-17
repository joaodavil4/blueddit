package com.jp.blueddit.ui.view.feed

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jp.blueddit.model.Post
import com.jp.blueddit.service.PostRepository
import com.jp.blueddit.service.RedditChildrenResponse
import com.jp.blueddit.service.RedditNewsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FeedViewModel(override val coroutineContext: CoroutineContext, private val repository: PostRepository) : ViewModel(), CoroutineScope {
    private val _posts = arrayListOf<Post>()

    var isRefreshing = MutableLiveData(false)
    var lazyListState: LazyListState? = null
    val posts = MutableLiveData<List<Post>>(listOf())
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
            repository.getApiData(skip)
        }.onSuccess {
            addPosts(it.body()?.data?.children?.map(RedditChildrenResponse::data)!!)
        }.onFailure {
            it.localizedMessage

        }
    }

    private suspend fun loadMore() {
        runCatching {
            repository.getApiData(skip)
        }.onSuccess {
            val list = (it.body()?.data?.children?.map(RedditChildrenResponse::data)!!)
            if (list.isEmpty()) {
                reachEnd = true
            } else {
                addPosts(list)
            }
        }
        isRefreshing.value = false

    }

    private fun addPosts(list: List<Post>) {
        launch {
            val filtered = list.filter { !_posts.contains(it) }
            skip += list.size
            _posts.addAll(filtered)
            posts.value = _posts.toList()
        }
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

    @Suppress("UNCHECKED_CAST")
    class FeedFactory(
        private val repository: PostRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedViewModel(Dispatchers.Main, repository) as T
        }
    }
}