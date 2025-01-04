package com.example.githubtrending.repository

import com.example.githubtrending.model.dto.doforce.DoForceRepositoryDto
import com.example.githubtrending.model.dto.github.SearchUserResponseDto
import kotlinx.coroutines.flow.Flow

interface DoForceRepository {

    fun fetchTrendingRepositories(): Flow<List<DoForceRepositoryDto>>

    fun fetchTrendingDevelopers(): Flow<SearchUserResponseDto>
}