package com.example.kaar.presentation.auth


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kaar.common.utils.RegisterFieldsState
import com.example.kaar.common.utils.RegisterValidation
import com.example.kaar.common.utils.User
import com.example.kaar.common.utils.validateEmail
import com.example.kaar.common.utils.validatePassword
import com.example.kaar.data.local.PrefManager
import com.example.kaar.domain.AuthUiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel
@Inject constructor(
    private val firebaseAuth:FirebaseAuth,
    private val sp: PrefManager
)
    : ViewModel()
{
    private val _authResult = MutableLiveData<AuthUiState>()
    val authResult: LiveData<AuthUiState> get() = _authResult

    private val _validation = Channel<RegisterFieldsState>()
    val validation = _validation.receiveAsFlow()


    fun createAccountWithEmailAndPassword(user: User, password: String) {
        viewModelScope.launch {
            if (checkValidation(user, password)) {
                _authResult.value = AuthUiState.Loading

                firebaseAuth.createUserWithEmailAndPassword(user.email, password)
                    .addOnSuccessListener {
                        it.user?.let {
                            _authResult.value = AuthUiState.Success("User created Succesfuly!")
                            sp.saveUsername(user.email)
                            sp.savePassword(password)
                        }
                    }.addOnFailureListener {
                        _authResult.value = it.localizedMessage?.let {
                          errorMsg -> AuthUiState.Error(errorMsg)
                        }
                    }
            }
            else {
                val registerFieldsState = RegisterFieldsState(
                    validateEmail(user.email),
                    validatePassword(password)
                )
                _validation.send(registerFieldsState)
            }
        }
    }

    private fun checkValidation(user: User, password: String): Boolean {
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)

        val register = emailValidation is RegisterValidation.Success &&
                passwordValidation is RegisterValidation.Success
        return register
    }


    fun saveEmail(email: String) {
        sp.saveUsername(email)
    }

    fun savePassword(password: String) {
        sp.savePassword(password)
    }


}


