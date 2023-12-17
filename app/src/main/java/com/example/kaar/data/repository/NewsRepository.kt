package com.example.kaar.data.repository


import com.example.kaar.common.utils.Resource
import com.example.kaar.data.api.NewsApi
import com.example.kaar.model.Article
import com.example.kaar.model.NewsResponseModelItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi,
) {

    suspend fun getArticlesData(): Flow<Resource<List<Article>>> =flow{
        emit(Resource.Loading)
        val response =api.getHeadlines()
        if(response.isSuccessful){
            response.body()?.let{
                emit(Resource.Success(it.articles))
            }

        }
    }.catch {
        emit(Resource.Error(it))
    }.flowOn(Dispatchers.IO)
}