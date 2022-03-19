package com.jp.blueddit.ui.compositions

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jp.blueddit.R
import com.jp.blueddit.extensions.getPostTime
import com.jp.blueddit.model.Post

import java.util.*

@Preview
@Composable
private fun Preview() {

    val post = Post(
        author = "joao",
        id = "1",
        title = "Man trying to return a dog's toy gets tricked into playing fetch",
        thumbnail = "",
        num_comments = 2,
        created_utc = 1411946514
    )
    PostItem(post = post)
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PostItem(
    post: Post,
    onProfileClickTap: () -> Unit = {}
) {

    val width = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier = Modifier
        .background(color = colorResource(id = R.color.white))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clickable { onProfileClickTap.invoke() }
                .padding(start = 16.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LoadCircularImage(
                imageUrl = post.thumbnail,
                size = 35.dp,
                placeholder = R.drawable.ic_android
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = post.author,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = colorResource(
                        id = R.color.black
                    )
                )

                val date = Date(post.created_utc.toLong())
                Text(
                    text = Date().getPostTime(date),
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = colorResource(
                        id = R.color.gray
                    )
                )
            }

            Box {
                Icon(
                    painter = painterResource(id = R.drawable.ic_new),
                    contentDescription = null,
                    tint = colorResource(id = R.color.colorPrimary)
                )
            }

        }

        Column(
            modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, bottom = 4.dp)) {

            Text(
                text = post.title,
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
                color = colorResource(
                    id = R.color.black
                )
            )
        }


        Box(modifier = Modifier
            .size(width)
            .wrapContentHeight(),
            contentAlignment = Alignment.TopCenter) {
            NetworkImage(
                modifier = Modifier
                    .size(width)
                    .pointerInput(Unit) {

                    },
                imageUrl = post.thumbnail,
                placeholder = R.drawable.ic_post_placeholder
            )
        }

        Row(verticalAlignment= Alignment.CenterVertically) {

            IconButton(onClick = {
                //showAlertMenu(context, isOwner, onDelete = onDelete, onEdit = onEdit)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_comment),
                    contentDescription = null,
                    tint = colorResource(id = R.color.black)
                )
            }

            Text(
                text = post.num_comments.toString(),
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                color = colorResource(
                    id = R.color.black
                )
            )
        }
    }
}


