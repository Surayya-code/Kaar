package com.example.kaar.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
   )
    : ViewModel() {
    private val _auth = MutableLiveData<Boolean>()
    //private lateinit var firebaseAuth: FirebaseAuth
    val auth: LiveData<Boolean> get() = _auth




    init{
        checkAuth()
    }

    private fun checkAuth() {
        viewModelScope.launch {
            delay(3000)
            _auth.value = firebaseAuth.currentUser ==null
        }
    }
}