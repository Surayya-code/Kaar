package com.example.kaar.presentation.home

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
class HomeViewModel @Inject constructor(
    private val movieRepository: NewsRepository): ViewModel() {

    private val _getNews= MutableLiveData<NewsUiState>()
    val getNews: LiveData<NewsUiState> = _getNews


    init {
        getAllNews()
    }

    private fun getAllNews() {
        viewModelScope.launch {
            movieRepository.getNewsData().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _getNews.value = NewsUiState.Success(it.data)

                    }

                    is Resource.Error -> {
                        _getNews.value = NewsUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _getNews.value = NewsUiState.Loading
                    }
                }
            }
        }
    }

}