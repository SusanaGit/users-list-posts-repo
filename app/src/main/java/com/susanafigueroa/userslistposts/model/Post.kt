package com.susanafigueroa.userslistposts.model

import kotlinx.serialization.Serializable

@Serializable
data class Post (
    val title: String,
    val body: String
)