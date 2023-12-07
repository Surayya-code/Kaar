package com.example.kaar.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaar.common.utils.RegisterFieldsState
import com.example.kaar.common.utils.RegisterValidation
import com.example.kaar.common.utils.Resource
import com.example.kaar.common.utils.User
import com.example.kaar.common.utils.validateEmail
import com.example.kaar.common.utils.validatePassword
import com.example.kaar.data.local.PrefManager
import com.example.kaar.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel  @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val repository: AuthRepository,
    private val sp: PrefManager
): ViewModel(){

    private val _authResult = MutableLiveData<Resource<Any>>()
    val authResult: LiveData<Resource<Any>> get() = _authResult

    private val _validation = Channel<RegisterFieldsState>()
    val validation = _validation.receiveAsFlow()

    private val _resetPassword = MutableLiveData<Resource<Any>>()
    val resetPassword:LiveData<Resource<Any>> get() = _resetPassword


    fun loginUser(user: User, password:String){
        viewModelScope.launch {
            repository.login(user.email, password).collectLatest{
                if(checkValidation(user,password)){
                    _authResult.value=Resource.Loading

                firebaseAuth.signInWithEmailAndPassword(
                    user.email,password
                ).addOnSuccessListener {
                    it.user?.let{
                        _authResult.value=Resource.Success(it)
                    }
                }.addOnFailureListener {
                    _authResult.value = Resource.Error(it)
                }
                }else{
                    val registerFieldsState = RegisterFieldsState(
                        validateEmail(user.email),
                        validatePassword(password)
                    )
                }
           }
        }
    }
   fun checkValidation(user: User,password: String):Boolean{
       val emailValidation = validateEmail(user.email)
       val passwordValidation = validatePassword(password)

       val register =
           emailValidation is RegisterValidation.Success &&
            passwordValidation is RegisterValidation.Success
       return  register

   }

  fun resetPassword(email:String){
      viewModelScope.launch {
          _resetPassword.value=Resource.Loading
      }
      firebaseAuth.sendPasswordResetEmail(email).addOnSuccessListener {
          _resetPassword.value = Resource.Success(email)
      }.addOnFailureListener {
          _resetPassword.value = Resource.Error(it)
      }
  }

    fun saveEmail(email: String) {
        sp.saveUsername(email)
    }

    fun savePassword(password: String) {
        sp.savePassword(password)
    }
}