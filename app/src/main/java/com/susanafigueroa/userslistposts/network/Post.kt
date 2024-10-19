package com.susanafigueroa.userslistposts.network

import kotlinx.serialization.Serializable

@Serializable
data class Post (
    val title: String,
    val body: String
)