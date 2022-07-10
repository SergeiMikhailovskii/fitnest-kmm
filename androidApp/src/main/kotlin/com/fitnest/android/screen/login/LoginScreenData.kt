package com.fitnest.android.screen.login

internal data class LoginScreenData(
    val login: String? = null,
    val password: String? = null,
    val hasLoginFocus: Boolean = false,
    val hasPasswordFocus: Boolean = false,
    val isPasswordVisible: Boolean = false
)
