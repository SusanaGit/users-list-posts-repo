package com.susanafigueroa.userslistposts.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.susanafigueroa.userslistposts.R
import com.susanafigueroa.userslistposts.model.User

@Composable
fun UsersScreen(
    usersListUiState: UsersListUiState,
    onUserClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Box (
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(R.drawable.background_img_users),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha=0.8f))
        )
        when(usersListUiState) {
            is UsersListUiState.Loading -> LoadingScreen(
                modifier = modifier.fillMaxSize()
            )

            is UsersListUiState.Success -> ResultScreen(
                users = usersListUiState.users,
                onUserClicked = onUserClicked,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = contentPadding.calculateTopPadding()
                    )
            )

            is UsersListUiState.Error -> ErrorScreen(
                modifier = modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun ResultScreen(
    users: List<User>,
    onUserClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(12.dp)
        ) {
            ShowUsers(
                listUsers = users,
                onUserClicked = onUserClicked
            )
        }
    }
}

@Composable
fun ShowUsers(listUsers: List<User>, onUserClicked: (Int) -> Unit) {
    Column{
        Row {
            Text(
                text = "Name",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                color = Color(0xFFB86E14)
            )
            Text(
                text = "WebSite",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                color = Color(0xFFB86E14)
            )
        }
        listUsers.forEach { user ->
            Row(
                modifier = Modifier
                    .clickable {onUserClicked(user.id)}
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = user.name,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF975300)
                )
                Text(
                    text = user.website,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF975300)
                )
            }
        }
    }
}