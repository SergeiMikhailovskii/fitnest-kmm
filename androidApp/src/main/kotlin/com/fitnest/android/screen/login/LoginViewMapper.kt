package com.fitnest.android.screen.login

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import com.fitnest.android.R
import com.fitnest.domain.entity.request.ForgetPasswordRequest
import com.fitnest.domain.entity.response.LoginPageResponse

internal class LoginViewMapper(
    private val context: Context
) {

    @Composable
    fun getLoginAnnotatedString() = buildAnnotatedString {
        val str = context.getString(R.string.login_register)
        val registerSpan = context.getString(R.string.login_register_span)
        val startIndex = str.indexOf(registerSpan)
        val endIndex = startIndex + registerSpan.length
        append(str)
        addStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.tertiary,
                textDecoration = TextDecoration.Underline
            ),
            start = startIndex,
            end = endIndex
        )
        addStringAnnotation(
            tag = LoginScreenConsts.LOGIN_SPAN_TAG,
            annotation = "",
            start = startIndex,
            end = endIndex
        )
    }

    fun mapScreenDataToLoginFields(screenData: LoginScreenData) =
        LoginPageResponse.LoginPageFields(screenData.login, screenData.password)

    fun mapScreenDataToForgetPasswordRequest(screenData: LoginScreenData) =
        ForgetPasswordRequest(login = screenData.login)
}