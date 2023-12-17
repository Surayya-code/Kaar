package com.example.kaar.domain

import com.example.kaar.model.Source
import retrofit2.Response

sealed class SourceUiState {
    object Loading : SourceUiState()
    data class Success(val data: Response<List<Source>>) : SourceUiState()
    data class Error(val message: String) : SourceUiState()
}