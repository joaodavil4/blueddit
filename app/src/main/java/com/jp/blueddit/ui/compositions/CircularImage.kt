package com.jp.blueddit.ui.compositions

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jp.blueddit.R

@Composable
fun CircularImage(
    painter: Painter,
    size: Dp,
    elevation: Dp = 0.dp,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    border: Dp = 0.dp,
    borderColor: Color = colorResource(id = R.color.black)
) {

    val modifier = if (border.value > 0) {
        Modifier
            .size(size)
            .clip(CircleShape)
            .shadow(elevation = elevation, shape = CircleShape)
            .border(width = border, color = borderColor, shape = CircleShape)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onLongClick.invoke() },
                    onTap = { onClick.invoke() }
                )
            }
    } else {
        Modifier
            .size(size)
            .clip(CircleShape)
            .shadow(elevation = elevation, shape = CircleShape)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onLongClick.invoke() },
                    onTap = { onClick.invoke() }
                )
            }
    }

    Image(
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
    )
}