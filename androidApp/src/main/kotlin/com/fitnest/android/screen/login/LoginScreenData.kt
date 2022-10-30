package com.fitnest.android.screen.login

import com.fitnest.domain.entity.response.LoginPageResponse
import com.fitnest.domain.exception.LoginPageValidationException

internal data class LoginScreenData(
    val login: String? = null,
    val password: String? = null,
    val isPasswordVisible: Boolean = false,
    val validationSchema: LoginPageResponse.LoginPageValidationSchema? = null,
    val exception: LoginPageValidationException? = null
)
