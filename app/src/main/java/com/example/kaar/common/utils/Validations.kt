package com.example.kaar.common.utils


fun validateUsername(username: String): RegisterValidation {

    if (username.isEmpty())
        return RegisterValidation.Failed("Username cannot be empty")

    if (username.length < 6) {
        return RegisterValidation.Failed("Username must be at least 6 characters")
    }

    if (!username[0].isUpperCase()) {
        return RegisterValidation.Failed("The first letter of the username must be capitalized")
    }

    return RegisterValidation.Success
}

fun validatePassword(password: String): RegisterValidation {

    if (password.isEmpty())
        return RegisterValidation.Failed("Username cannot be empty")

    if (password.length < 6) {
        return RegisterValidation.Failed("Username must be at least 6 characters")
    }


    return RegisterValidation.Success
}

fun validateConfirmPassword(password: String, confirmPassword: String): RegisterValidation {

    if (password != confirmPassword)
      return RegisterValidation.Failed("Passwords do not match")


    return RegisterValidation.Success
}
