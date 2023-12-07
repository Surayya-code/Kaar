package com.example.kaar.data.api

import com.example.kaar.model.ProductResponseModelItem
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductResponseModelItem>>
}