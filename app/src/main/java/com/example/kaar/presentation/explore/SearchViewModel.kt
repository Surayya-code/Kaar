package com.example.kaar.presentation.explore

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kaar.common.utils.Resource
import com.example.kaar.data.repository.NewsRepository
import com.example.kaar.domain.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    application: Application
): AndroidViewModel(application) {

    private val _news = MutableLiveData<NewsUiState>()
    val news: LiveData<NewsUiState> = _news


}