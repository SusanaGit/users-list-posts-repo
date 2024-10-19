package com.susanafigueroa.userslistposts.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.susanafigueroa.userslistposts.network.Post
import com.susanafigueroa.userslistposts.network.User
import com.susanafigueroa.userslistposts.network.UsersListPostsApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UsersListUiState {
    data class Success(val users: List<User>): UsersListUiState
    object Error: UsersListUiState
    object Loading: UsersListUiState
}

sealed interface PostsListUiState {
    data class Success(val posts: List<Post>): PostsListUiState
    object Error: PostsListUiState
    object Loading: PostsListUiState
}

class UsersListPostsViewModel: ViewModel() {
    var usersListUiState: UsersListUiState by mutableStateOf(UsersListUiState.Loading)
        private set

    var postsListUiState: PostsListUiState by mutableStateOf(PostsListUiState.Loading)
        private set

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {
                val listResult = UsersListPostsApi.retrofitService.getUsers()
                usersListUiState = UsersListUiState.Success(
                    listResult
                )

            } catch (e: IOException) {
                println(e.message)
                usersListUiState = UsersListUiState.Error
            }
        }
    }

    fun userSelected(userId: Int) {
        viewModelScope.launch {
            try {
                val listPostsResult = UsersListPostsApi.retrofitService.getPosts(userId)
                postsListUiState = PostsListUiState.Success(
                    listPostsResult
                )

            } catch (e: IOException) {
                println(e.message)
                postsListUiState = PostsListUiState.Error
            }
        }
    }
}