package com.example.githubtrending.model.dto.github

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val url: String,
    val type: String,
    val siteAdmin: Boolean
)