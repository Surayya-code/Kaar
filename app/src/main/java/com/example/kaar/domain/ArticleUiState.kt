package com.example.kaar.domain

import androidx.lifecycle.MutableLiveData
import com.example.kaar.model.Article
import com.example.kaar.model.NewsResponseModelItem
import retrofit2.Response

sealed class ArticleUiState {
    object Loading : ArticleUiState()
    data class Success(val data: List<Article>) : ArticleUiState()
    data class Error(val message: String?) : ArticleUiState()
}