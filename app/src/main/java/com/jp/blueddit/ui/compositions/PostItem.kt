package com.jp.blueddit.ui.compositions

import android.content.Context
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.jp.blueddit.R
import com.jp.blueddit.model.Post

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
private fun Preview() {

    val post = Post(
        objectId = "sdadasd",
        medias = "",
        text = "test",
        postedBy = "Test",
        createdAt = "2021-09-09T17:19:17.015Z"
    )
    PostItem(post = post)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PostItem(
    post: Post,
    onDelete: () -> Unit = {},
    onEdit: () -> Unit = {},
    onDoubleTap: () -> Unit = {},
    onProfileClickTap: () -> Unit = {},
    onLinkClick: (String) -> Unit = {}
) {

    val width = LocalConfiguration.current.screenWidthDp.dp

    Column(modifier = Modifier.background(color = colorResource(id = R.color.white))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clickable { onProfileClickTap.invoke() }
                .padding(start = 16.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoadCircularImage(
                imageUrl = post.postedBy,
                size = 35.dp,
                placeholder = R.drawable.ic_android
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = post.postedBy,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = colorResource(
                        id = R.color.black
                    )
                )
                Text(
                    text = "ijij",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = colorResource(
                        id = R.color.black
                    )
                )
            }
            Box {

                IconButton(onClick = {
                    //showAlertMenu(context, isOwner, onDelete = onDelete, onEdit = onEdit)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        tint = colorResource(id = R.color.black)
                    )
                }
            }
        }

        Box(modifier = Modifier.size(width), contentAlignment = Alignment.Center) {
            val showLike = remember { mutableStateOf(false) }
            NetworkImage(
                modifier = Modifier
                    .size(width)
                    .pointerInput(Unit) {

                    },
                imageUrl = "",
                placeholder = R.drawable.ic_post_placeholder
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}


