package com.susanafigueroa.userslistposts.data

import com.susanafigueroa.userslistposts.model.Post
import com.susanafigueroa.userslistposts.model.User
import com.susanafigueroa.userslistposts.network.UsersListPostsApi

interface UsersListPostsRepository {
    suspend fun getUsers(): List<User>
    suspend fun getPosts(userId: Int): List<Post>
}

class NetworkUsersListPostsRepository(): UsersListPostsRepository {
    override suspend fun getUsers(): List<User> {
        return UsersListPostsApi.retrofitService.getUsers()
    }

    override suspend fun getPosts(userId: Int): List<Post> {
        return UsersListPostsApi.retrofitService.getPosts(userId)
    }
}
