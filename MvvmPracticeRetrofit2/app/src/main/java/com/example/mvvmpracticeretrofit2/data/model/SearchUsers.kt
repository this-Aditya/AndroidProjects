package com.example.mvvmpracticeretrofit2.data.model

data class SearchUsers(
    val incomplete_results: Boolean,
    val items: List<Users>,
    val total_count: Int
)