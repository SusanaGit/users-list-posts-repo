package com.susanafigueroa.userslistposts.data

import com.susanafigueroa.userslistposts.model.Post
import com.susanafigueroa.userslistposts.model.User
import com.susanafigueroa.userslistposts.network.UsersListPostsApiService

interface UsersListPostsRepository {
    suspend fun getUsers(): List<User>
    suspend fun getPosts(userId: Int): List<Post>
}

class NetworkUsersListPostsRepository(
    private val usersListPostsApiService: UsersListPostsApiService
): UsersListPostsRepository {
    override suspend fun getUsers(): List<User> =
        usersListPostsApiService.getUsers()

    override suspend fun getPosts(userId: Int): List<Post> =
        usersListPostsApiService.getPosts(userId)
}
