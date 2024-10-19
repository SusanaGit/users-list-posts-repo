package com.susanafigueroa.userslistposts.data

import com.susanafigueroa.userslistposts.network.Post
import com.susanafigueroa.userslistposts.network.User

interface UsersListPostsRepository {
    suspend fun getUsers(): List<User>
    suspend fun getPosts(): List<Post>
}

