package com.jp.blueddit.ui.compositions

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.jp.blueddit.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun LoadCircularImage(
    imageUrl: String?,
    size: Dp,
    @DrawableRes placeholder: Int,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
    elevation: Dp = 0.dp,
    border: Dp = 0.dp,
    borderColor: Color = colorResource(id = R.color.black)
) {
    val painter = if (imageUrl != null) {
        rememberImagePainter(
            data = imageUrl,
            builder = {
                crossfade(true)
                transformations(CircleCropTransformation())
                placeholder(placeholder)
            }
        )
    } else {
        painterResource(id = placeholder)
    }

    CircularImage(
        painter = painter,
        size = size,
        elevation = elevation,
        onClick = onClick,
        onLongClick = onLongClick,
        border = border,
        borderColor = borderColor
    )
}