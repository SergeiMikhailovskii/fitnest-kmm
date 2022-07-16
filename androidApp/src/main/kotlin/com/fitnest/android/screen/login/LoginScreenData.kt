package com.fitnest.android.screen.login

import com.fitnest.domain.entity.response.LoginPageResponse

internal data class LoginScreenData(
    val login: String? = null,
    val password: String? = null,
    val hasLoginFocus: Boolean = false,
    val hasPasswordFocus: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val validationSchema: LoginPageResponse.LoginPageValidationSchema? = null
)
