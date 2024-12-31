package com.example.githubtrending.repository

import com.example.githubtrending.model.dto.github.SearchResponseDto
import com.example.githubtrending.repository.http.GitHubHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val gitHubHttpClient: GitHubHttpClient,
) : GitHubRepository {

    override fun fetch(query: String, page: Int): Flow<SearchResponseDto> {
        return flow {
            emit(gitHubHttpClient.fetchRepository(query, page))
        }.flowOn(Dispatchers.IO)
    }

}