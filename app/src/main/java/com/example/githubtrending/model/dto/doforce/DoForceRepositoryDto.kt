package com.example.githubtrending.model.dto.doforce

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DoForceRepositoryDto(
    @SerialName("repo") val repo: String,
    @SerialName("desc") val desc: String,
    @SerialName("lang") val lang: String,
    @SerialName("stars") val stars: Int,
    @SerialName("forks") val forks: Int,
    @SerialName("build_by") val buildBy: List<BuilderDto>,
    @SerialName("change") val change: Int
)

@Serializable
data class BuilderDto(
    @SerialName("avatar") val avatar: String,
    @SerialName("by") val by: String
)
