package com.example.kaar.data.api

import android.content.Context
import com.example.kaar.common.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    @ApplicationContext private val context: Context) : Interceptor {

override fun intercept(chain: Interceptor.Chain): Response {
    val apiKey = Constants.API_KEY
    val request = chain.request()
    val newRequest = request.newBuilder()
        .header("Authorization", "Bearer $apiKey")
        .build()
    return chain.proceed(newRequest)
    }
}
