package com.example.githubtrending.repository.http

import com.example.githubtrending.model.dto.github.SearchResponseDto
import com.example.githubtrending.model.dto.github.SearchUserResponseDto
import retrofit2.http.GET

interface DoForceHttpClient {

    @GET("repo")
    suspend fun fetchTrendingRepositories(): SearchResponseDto

    @GET("user")
    suspend fun fetchTrendingDevelopers(): SearchUserResponseDto
}
