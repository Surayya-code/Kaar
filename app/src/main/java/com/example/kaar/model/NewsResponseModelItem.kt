package com.example.kaar.model


import com.google.gson.annotations.SerializedName

data class NewsResponseModelItem(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)