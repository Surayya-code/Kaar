package com.example.kaar.domain

import com.example.kaar.model.Article
import com.example.kaar.model.NewsResponseModelItem

sealed class NewsUiState {

    object Loading : NewsUiState()
    data class Success(val data: List<Article>) : NewsUiState()
    data class Error(val message: String) : NewsUiState()
}