package com.example.githubtrending.model.dto.github


import com.example.githubtrending.ui.paging.PagingCursor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(

    @SerialName("total_count")
    val totalCount: Int,
    @SerialName("incomplete_results")
    val isIncompleteResults: Boolean,
    @SerialName("items")
    val items: List<RepositoryDto>
) : PagingCursor {

    override var nextCursor: String? = null

}