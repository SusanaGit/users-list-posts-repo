package com.susanafigueroa.userslistposts.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.susanafigueroa.userslistposts.network.UsersListPostsApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val usersListPostsRepository: UsersListPostsRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl =
        "https://jsonplaceholder.typicode.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: UsersListPostsApiService by lazy {
        retrofit.create(UsersListPostsApiService::class.java)
    }

    override val usersListPostsRepository: UsersListPostsRepository by lazy {
        NetworkUsersListPostsRepository(retrofitService)
    }
}