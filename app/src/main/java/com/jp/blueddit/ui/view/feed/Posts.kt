package com.jp.blueddit.ui.view.feed


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jp.blueddit.ui.compositions.PostItem


@Composable
fun Posts(
    pageIndex: Int,
    viewModel: FeedViewModel
) {
    if (viewModel.lazyListState == null) {
        viewModel.lazyListState = rememberLazyListState()
    }

    val isRefreshing by viewModel.isRefreshing.observeAsState(false)
    val posts by viewModel.posts.observeAsState(listOf())

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refresh() }
    ) {
        LazyColumn(
            state = viewModel.lazyListState ?: rememberLazyListState(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val threshold = 3
            val lastIndex = if (pageIndex == 0) posts.lastIndex + 1 else posts.lastIndex

            itemsIndexed(posts) { index, item ->
                if ((index + threshold >= lastIndex) && !viewModel.reachEnd) {
                    SideEffect { viewModel.loadItems(init = false) }
                }

                PostItem(
                    post = item
                )
            }
        }
    }
}
