package com.fitnest.android.screen.registration

import androidx.lifecycle.viewModelScope
import com.fitnest.android.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateAccountRegistrationViewModel : BaseViewModel() {

    private val screenData = CreateAccountRegistrationScreenData.init()

    private val _screenDataFlow = MutableStateFlow(screenData.copy())
    internal val screenDataFlow = _screenDataFlow.asStateFlow()

    internal fun updateFirstName(name: String) {
        screenData.firstName = name
        updateScreenData()
    }

    internal fun updateLastName(lastName: String) {
        screenData.lastName = lastName
        updateScreenData()
    }

    internal fun updateEmail(email: String) {
        screenData.email = email
        updateScreenData()
    }

    internal fun updatePassword(password: String) {
        screenData.password = password
        updateScreenData()
    }

    internal fun changePasswordVisibility() {
        screenData.passwordVisible = !screenData.passwordVisible
        updateScreenData()
    }

    private fun updateScreenData() {
        viewModelScope.launch {
            _screenDataFlow.emit(screenData.copy())
        }
    }

}