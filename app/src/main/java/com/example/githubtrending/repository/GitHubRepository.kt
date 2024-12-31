package com.example.githubtrending.repository

import com.example.githubtrending.model.dto.github.SearchResponseDto
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {

    fun fetch(query: String, page: Int): Flow<SearchResponseDto>
}