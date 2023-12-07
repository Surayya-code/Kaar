package com.example.kaar.domain

import com.example.kaar.model.ProductResponseModelItem
import retrofit2.Response

sealed class NewsUiState {

    object Loading : NewsUiState()
    data class Success(val data: Response<List<ProductResponseModelItem>>) : NewsUiState()
    data class Error(val message: String) : NewsUiState()
}