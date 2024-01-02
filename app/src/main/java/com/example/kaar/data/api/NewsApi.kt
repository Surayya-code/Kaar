package com.example.kaar.data.api

import com.example.kaar.common.utils.Constants.API_KEY
import com.example.kaar.model.Article
import com.example.kaar.model.NewsResponseModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query ("country") countryCode: String="us",
        @Query ("page") pageNumber: Int =1,
        @Query("apiKey") apiKey: String = API_KEY,
        ): Response<NewsResponseModelItem>



    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q") searchQuery: String,
        @Query("page") pageNumber: Int=1,
        @Query("apiKey") apiKey: String = API_KEY,
        ): Response<NewsResponseModelItem>

    /*
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(@Query("api_key") apiKey: String = API_KEY ): NewsResponseModelItem

   */

}