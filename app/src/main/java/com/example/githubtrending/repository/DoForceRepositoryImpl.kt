package com.example.githubtrending.repository

import android.util.Log
import com.example.githubtrending.model.dto.doforce.DoForceRepositoryDto
import com.example.githubtrending.model.dto.github.SearchResponseDto
import com.example.githubtrending.model.dto.github.SearchUserResponseDto
import com.example.githubtrending.repository.http.DoForceHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.SerializationException
import javax.inject.Inject

class DoForceRepositoryImpl @Inject constructor(
    private val doForceHttpClient: DoForceHttpClient,
) : DoForceRepository {

    override fun fetchTrendingRepositories(): Flow<List<DoForceRepositoryDto>> {
        return flow {
            try {
                val response = doForceHttpClient.fetchTrendingRepositories()
                Log.d("DoForceRepositoryImpl", "API Response: $response")
                emit(response)
            } catch (e: SerializationException) {
                Log.e("DoForceRepositoryImpl", "Deserialization error", e)
                throw e
            } catch (e: Exception) {
                Log.e("DoForceRepositoryImpl", "Error fetching trending repositories", e)
                throw e
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchTrendingDevelopers(): Flow<SearchUserResponseDto> {
        return flow {
            emit(doForceHttpClient.fetchTrendingDevelopers())
        }.flowOn(Dispatchers.IO)
    }
}