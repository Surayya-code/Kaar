package com.example.kaar.common.utils

sealed class RegisterValidation(){

    data object Success : RegisterValidation()

    data class Failed(val message:String) : RegisterValidation()
}

data class  RegisterFieldsState(
    val username: RegisterValidation,
    val password: RegisterValidation,

)
