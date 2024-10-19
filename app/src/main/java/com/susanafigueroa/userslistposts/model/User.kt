package com.susanafigueroa.userslistposts.model

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val id: Int,
    val name: String,
    val website: String
)