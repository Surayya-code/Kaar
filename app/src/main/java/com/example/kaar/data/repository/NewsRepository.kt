package com.example.kaar.data.repository

import com.example.kaar.common.utils.Resource
import com.example.kaar.data.api.NewsApi
import com.example.kaar.model.ProductResponseModelItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi,
) {
    fun getNewsData(): Flow<Resource<Response<List<ProductResponseModelItem>>>> = flow {
        emit(Resource.Loading)
        val response = api.getAllProducts()
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

}