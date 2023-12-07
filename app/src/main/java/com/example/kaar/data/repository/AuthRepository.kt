package com.example.kaar.data.repository

import com.example.kaar.common.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
) {
    suspend fun login(
        username:String,
        password:String
    ): Flow<Resource<AuthResult>> =
    flow {
        emit(Resource.Loading)
        val user = auth.signInWithEmailAndPassword(username, password).await()
        emit(Resource.Success(user))
    }.catch {
        emit(Resource.Error(it as Exception))
    }

    fun register(username: String, password: String): Flow<Resource<AuthResult>> =
        flow {
            emit(Resource.Loading)
            val user = auth.createUserWithEmailAndPassword(username, password).await()
            emit(Resource.Success(user))
        }.catch {
            emit(Resource.Error(it as Exception))
        }

}