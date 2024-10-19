package com.susanafigueroa.userslistposts.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.susanafigueroa.userslistposts.R
import com.susanafigueroa.userslistposts.model.Post

@Composable
fun PostsScreen(
    postsListUiState: PostsListUiState,
    modifier: Modifier = Modifier,
) {

    Box (
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(R.drawable.background_img_posts),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha=0.8f))
        )

        when(postsListUiState) {
            is PostsListUiState.Loading -> LoadingScreen(
                modifier = modifier.fillMaxSize()
            )

            is PostsListUiState.Success -> ResultPostsScreen(
                postsListUiState.posts,
                modifier = modifier
                    .fillMaxWidth()
            )

            is PostsListUiState.Error -> ErrorScreen(
                modifier = modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun ResultPostsScreen(posts: List<Post>, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(top = 110.dp)
        ) {
            ShowPosts(listPosts = posts)
        }
    }
}

@Composable
fun ShowPosts(listPosts: List<Post>) {
    Column {
        listPosts.forEach { post ->
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .background(
                        color = Color(0x19FFDC73),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFFB86E14),
                    modifier = Modifier
                        .padding(10.dp)
                )
                Text(
                    text = post.body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF975300),
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}