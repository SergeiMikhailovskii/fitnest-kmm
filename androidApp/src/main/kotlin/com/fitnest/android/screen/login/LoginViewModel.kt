package com.fitnest.android.screen.login

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import com.fitnest.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private var screenData = LoginScreenData()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    internal fun updateLogin(login: String) {
        screenData = screenData.copy(login = login)
        updateScreenData()
    }

    internal fun updatePassword(password: String) {
        screenData = screenData.copy(password = password)
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

    private fun updateScreenData() {
        viewModelScope.launch {
            _screenDataFlow.emit(screenData.copy())
        }
    }


}