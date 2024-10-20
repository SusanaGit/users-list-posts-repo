package com.susanafigueroa.userslistposts.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.susanafigueroa.userslistposts.model.Post
import com.susanafigueroa.userslistposts.model.User
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://jsonplaceholder.typicode.com"

private val json = Json {
    ignoreUnknownKeys = true
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface UsersListPostsApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("posts")
    suspend fun getPosts(@Query("userId") userId: Int): List<Post>
}