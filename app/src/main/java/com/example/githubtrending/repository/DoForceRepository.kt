package com.example.githubtrending.repository

import com.example.githubtrending.model.dto.github.SearchResponseDto
import com.example.githubtrending.model.dto.github.SearchUserResponseDto
import kotlinx.coroutines.flow.Flow

interface DoForceRepository {

    fun fetchTrendingRepositories(): Flow<SearchResponseDto>

    fun fetchTrendingDevelopers(): Flow<SearchUserResponseDto>
}