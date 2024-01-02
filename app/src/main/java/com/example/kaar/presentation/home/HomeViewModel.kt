package com.example.kaar.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaar.common.utils.Resource
import com.example.kaar.data.repository.NewsRepository
import com.example.kaar.domain.ArticleUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    private val _newsState = MutableLiveData<ArticleUiState>()
    val newsState: LiveData<ArticleUiState> = _newsState

    private val _trendNews = MutableLiveData<ArticleUiState>()
    val trendNews: LiveData<ArticleUiState> = _trendNews

   init{
    getArticle()
    getTrendNews()
   }

     fun getArticle(){
        viewModelScope.launch {
            newsRepository.getArticlesData().collectLatest {
                when(it){
                    is Resource.Success->{
                        _newsState.value = ArticleUiState.Success(it.data)
                    }
                    is Resource.Loading->{
                        _newsState.value = ArticleUiState.Loading
                    }
                    is Resource.Error->{
                        _newsState.value=ArticleUiState.Error(it.exception.message.toString())
                    }
                }
            }
        }
    }
     fun getTrendNews() {
        viewModelScope.launch {
            newsRepository.getArticlesData().collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _trendNews.value = ArticleUiState.Success(it.data)
                    }

                    is Resource.Error -> {
                        _trendNews.value = ArticleUiState.Error(it.exception.message.toString())
                    }

                    is Resource.Loading -> {
                        _trendNews.value = ArticleUiState.Loading
                    }
                }
            }
        }
    }

}