package com.example.githubtrending.model.dto.github

import kotlinx.serialization.Serializable

@Serializable
data class SearchUserResponseDto(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<UserDto>
)