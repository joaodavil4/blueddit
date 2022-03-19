package com.jp.blueddit.ui.compositions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jp.blueddit.R

@Composable
fun FullScreenDialog(showDialog:Boolean,
                     onClose:()->Unit,
                     imageUrl: String,
                     onIconClick: () -> Unit = {}) {
    if (showDialog) {
        Dialog(onDismissRequest =  onClose ) {
            Surface(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color.DarkGray
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        IconButton(onClick = onIconClick){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = null,
                                tint = colorResource(id = R.color.white)
                            )
                        }
                    }
                    NetworkImage(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        imageUrl = imageUrl,
                        placeholder = R.drawable.ic_post_placeholder
                    )
                }
            }
        }
    }
}