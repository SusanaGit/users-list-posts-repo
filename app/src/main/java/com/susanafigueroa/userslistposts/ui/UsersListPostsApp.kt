@file:OptIn(ExperimentalMaterial3Api::class)

package com.susanafigueroa.userslistposts.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.susanafigueroa.userslistposts.R
import com.susanafigueroa.userslistposts.ui.screens.PostsScreen
import com.susanafigueroa.userslistposts.ui.screens.UsersListPostsViewModel
import com.susanafigueroa.userslistposts.ui.screens.UsersScreen

enum class UsersListPostsScreen(@StringRes val title: Int) {
    Users(title = R.string.users_list),
    Posts(title = R.string.posts_list)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersListPostsApp(
    navController: NavHostController = rememberNavController()
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = when {
        backStackEntry?.destination?.route?.startsWith(UsersListPostsScreen.Users.name) == true -> {
            UsersListPostsScreen.Users
        }
        backStackEntry?.destination?.route?.startsWith(UsersListPostsScreen.Posts.name) == true -> {
            UsersListPostsScreen.Posts
        }
        else -> UsersListPostsScreen.Users
    }

    var currentUserId by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { UsersListPostsAppBar(
            scrollBehavior = scrollBehavior,
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = {
                navController.navigateUp()
            }
        )}
    ) { paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = UsersListPostsScreen.Users.name
            ) {
                composable(route = UsersListPostsScreen.Users.name) {
                    val usersListViewModel: UsersListPostsViewModel = viewModel(factory = UsersListPostsViewModel.Factory)
                    UsersScreen(
                        usersListUiState = usersListViewModel.usersListUiState,
                        onUserClicked = { id ->
                            currentUserId = id
                            navController.navigate("${UsersListPostsScreen.Posts.name}/$currentUserId")},
                        contentPadding = paddingValues)
                }
                composable(route = "${UsersListPostsScreen.Posts.name}/{id}") {
                    val postsListViewModel: UsersListPostsViewModel = viewModel()
                    if (currentUserId != null) {
                        postsListViewModel.userSelected(currentUserId!!)
                        PostsScreen(
                            postsListUiState = postsListViewModel.postsListUiState
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UsersListPostsAppBar(
    currentScreen: UsersListPostsScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
){
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                if (canNavigateBack) {
                    IconButton(
                        onClick = navigateUp,
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(
                                R.string.back_button
                            ),
                            tint = Color(0xFF975300)
                        )
                    }
                }
                Text(
                    text = stringResource(currentScreen.title),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color(0xFF975300)
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun UsersListPostsPreview() {
    UsersListPostsApp()
}
