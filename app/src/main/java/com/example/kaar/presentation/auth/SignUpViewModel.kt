package com.example.kaar.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaar.common.utils.RegisterFieldsState
import com.example.kaar.common.utils.RegisterValidation
import com.example.kaar.common.utils.User
import com.example.kaar.common.utils.validateConfirmPassword
import com.example.kaar.common.utils.validatePassword
import com.example.kaar.common.utils.validateUsername
import com.example.kaar.data.PrefManager
import com.example.kaar.domain.AuthUiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val firebaseAuth:FirebaseAuth,
    private val sp:PrefManager
): ViewModel()
{
    private val _authResult = MutableLiveData<AuthUiState>()
    val authResult: LiveData<AuthUiState> get() = _authResult

    private val _validation = Channel<RegisterFieldsState>()
    val validation = _validation.receiveAsFlow()


    fun createAccountWithUsernameAndPassword(user: User, password: String) {
        viewModelScope.launch {
            if (checkValidation(user, password)) {
                _authResult.value = AuthUiState.Loading

                firebaseAuth.createUserWithEmailAndPassword(user.username, password)
                    .addOnSuccessListener {
                        it.user?.let {
                            _authResult.value = AuthUiState.Success("User created Succesfuly!")
                            sp.saveUsername(user.username)
                            sp.savePassword(password)
                        }
                    }.addOnFailureListener {
                        _authResult.value = it.localizedMessage?.let { errorMsg ->
                            AuthUiState.Error(errorMsg)
                        }
                    }
            } else {
                val registerFieldsState = RegisterFieldsState(
                    validateUsername(user.username),
                    validatePassword(password)
                )
                _validation.send(registerFieldsState)
            }
        }
    }

    private fun checkValidation(user: User, password: String): Boolean {
        val usernameValidation = validateUsername(user.username)
        val passwordValidation = validatePassword(password)

        val register = usernameValidation is RegisterValidation.Success &&
                passwordValidation is RegisterValidation.Success
        return register
    }


    fun saveUsername(username: String) {
        sp.saveUsername(username)
    }

    fun savePassword(password: String) {
        sp.savePassword(password)
    }


}