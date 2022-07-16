package com.fitnest.android.screen.login

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.android.base.Route
import com.fitnest.domain.enum.FlowType
import com.fitnest.domain.exception.LoginPageValidationException
import com.fitnest.domain.usecase.auth.GetLoginPageUseCase
import com.fitnest.domain.usecase.validation.LoginPageValidationUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val getLoginPageUseCase: GetLoginPageUseCase,
    private val validator: LoginPageValidationUseCase,
    private val viewMapper: LoginViewMapper
) : BaseViewModel() {

    private var screenData = LoginScreenData()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is LoginPageValidationException) {
            screenData = screenData.copy(exception = throwable)
            updateScreenData()
        }
    }

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val page = getLoginPageUseCase().getOrThrow()
            screenData = screenData.copy(
                login = page.fields?.login,
                password = page.fields?.password,
                validationSchema = page.validationSchema
            )
            updateScreenData()
        }
    }

    internal fun updateLogin(login: String) {
        screenData = screenData.copy(
            login = login,
            exception = screenData.exception?.copy(
                loginError = null,
                passwordError = screenData.exception?.passwordError
            )
        )
        updateScreenData()
    }

    internal fun updatePassword(password: String) {
        screenData = screenData.copy(
            password = password,
            exception = screenData.exception?.copy(
                loginError = screenData.exception?.loginError,
                passwordError = null
            )
        )
        updateScreenData()
    }

    internal fun updateLoginFocus(isFocused: Boolean) {
        screenData = screenData.copy(
            hasLoginFocus = isFocused
        )
        updateScreenData()
    }

    internal fun updatePasswordFocus(isFocused: Boolean) {
        screenData = screenData.copy(
            hasPasswordFocus = isFocused
        )
        updateScreenData()
    }

    internal fun changePasswordVisibility() {
        screenData = screenData.copy(
            isPasswordVisible = !screenData.isPasswordVisible
        )
        updateScreenData()
    }

    internal fun goToRegistration() {
        handleRoute(Route.Proxy(FlowType.REGISTRATION))
    }

    internal fun validateAndLogin() {
        viewModelScope.launch(exceptionHandler) {
            val fields = viewMapper.mapScreenDataToLoginFields(screenData)
            validator(fields, screenData.validationSchema).getOrThrow()
        }
    }

    private fun updateScreenData() {
        viewModelScope.launch {
            _screenDataFlow.emit(screenData.copy())
        }
    }
}