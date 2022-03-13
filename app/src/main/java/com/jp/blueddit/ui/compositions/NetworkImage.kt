package com.jp.blueddit.ui.compositions

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    @DrawableRes placeholder: Int,
    colorFilter: ColorFilter? = null
) {
    val painter = if (imageUrl != null) {
        rememberImagePainter(
            data = imageUrl,
            builder = {
                crossfade(true)
                placeholder(placeholder)
            }
        )
    } else {
        painterResource(id = placeholder)
    }

    Image(
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier,
        colorFilter = colorFilter
    )
}