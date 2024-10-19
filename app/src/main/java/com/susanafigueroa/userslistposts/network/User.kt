package com.susanafigueroa.userslistposts.network

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val id: Int,
    val name: String,
    val website: String
)