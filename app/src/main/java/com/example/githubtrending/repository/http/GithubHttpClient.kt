package com.example.githubtrending.repository.http

import com.example.githubtrending.model.dto.github.SearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubHttpClient {

    @GET("search/repositories")
    suspend fun fetchRepository(@Query("q") query: String, @Query("page") page: Int): SearchResponseDto

}