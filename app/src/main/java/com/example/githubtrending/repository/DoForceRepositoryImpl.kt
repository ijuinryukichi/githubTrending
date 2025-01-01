package com.example.githubtrending.repository

import com.example.githubtrending.model.dto.github.SearchResponseDto
import com.example.githubtrending.model.dto.github.SearchUserResponseDto
import com.example.githubtrending.repository.http.DoForceHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DoForceRepositoryImpl @Inject constructor(
    private val doForceHttpClient: DoForceHttpClient,
) : DoForceRepository {

    override fun fetchTrendingRepositories(): Flow<SearchResponseDto> {
        return flow {
            emit(doForceHttpClient.fetchTrendingRepositories())
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchTrendingDevelopers(): Flow<SearchUserResponseDto> {
        return flow {
            emit(doForceHttpClient.fetchTrendingDevelopers())
        }.flowOn(Dispatchers.IO)
    }
}