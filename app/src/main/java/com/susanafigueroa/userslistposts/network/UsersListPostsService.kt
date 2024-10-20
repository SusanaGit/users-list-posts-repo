package com.susanafigueroa.userslistposts.network

import com.susanafigueroa.userslistposts.model.Post
import com.susanafigueroa.userslistposts.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersListPostsApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("posts")
    suspend fun getPosts(@Query("userId") userId: Int): List<Post>
}