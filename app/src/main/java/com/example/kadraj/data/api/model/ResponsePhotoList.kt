package com.example.kadraj.data.api.model

data class ResponsePhotoList(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int
)