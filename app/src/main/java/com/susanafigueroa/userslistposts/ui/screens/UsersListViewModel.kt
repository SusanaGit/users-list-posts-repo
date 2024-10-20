package com.susanafigueroa.userslistposts.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.susanafigueroa.userslistposts.UsersListPostsApplication
import com.susanafigueroa.userslistposts.data.NetworkUsersListPostsRepository
import com.susanafigueroa.userslistposts.data.UsersListPostsRepository
import com.susanafigueroa.userslistposts.model.Post
import com.susanafigueroa.userslistposts.model.User
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

class UsersListPostsViewModel(private val usersListPostsRepository: UsersListPostsRepository): ViewModel() {
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

                val listResult = usersListPostsRepository.getUsers()

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

                val listPostsResult = usersListPostsRepository.getPosts(userId)

                postsListUiState = PostsListUiState.Success(
                    listPostsResult
                )

            } catch (e: IOException) {
                println(e.message)
                postsListUiState = PostsListUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as UsersListPostsApplication)
                val usersListPostsRepository = application.container.usersListPostsRepository
                UsersListPostsViewModel(usersListPostsRepository = usersListPostsRepository)
            }
        }
    }
}