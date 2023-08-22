package com.example.kadraj.data.api.model.collection

import com.example.kadraj.data.api.model.collection.Collection

data class CollectionResponse(
    val collections: List<Collection>,
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val prev_page: String,
    val total_results: Int
)