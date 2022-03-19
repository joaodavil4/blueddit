package com.jp.blueddit.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.jp.blueddit.service.PostRepositoryClass
import com.jp.blueddit.ui.theme.BluedditTheme
import com.jp.blueddit.ui.view.feed.FeedViewModel
import com.jp.blueddit.ui.view.feed.Posts

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BluedditTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val repositoryClass = PostRepositoryClass()
                    val viewModel = ViewModelProvider(
                        this,
                        FeedViewModel.FeedFactory(repositoryClass))[FeedViewModel::class.java]
                    viewModel.loadItems(true)
                    Posts(pageIndex = 0, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BluedditTheme {
        Greeting("Android")
    }
}