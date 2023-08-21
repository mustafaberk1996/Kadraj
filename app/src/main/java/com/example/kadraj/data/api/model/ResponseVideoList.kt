package com.example.kadraj.data.api.model

data class ResponseVideoList(
    val page: Int,
    val per_page: Int,
    val total_results: Int,
    val url: String,
    val videos: List<Video>
)