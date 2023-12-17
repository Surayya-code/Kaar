package com.example.kaar.presentation.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaar.common.utils.Resource
import com.example.kaar.data.repository.NewsRepository
import com.example.kaar.domain.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository)
    : ViewModel() {
    private val _news= MutableLiveData<NewsUiState>()
    val trailer: LiveData<NewsUiState> = _news


}