package com.example.kaar.common.utils

sealed class Resource <out T>{
    data class Success<out T : Any>(val data: T) : Resource<T>()

    data class Error(val exception: Throwable) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}