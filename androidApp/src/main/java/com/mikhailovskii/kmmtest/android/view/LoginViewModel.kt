package com.mikhailovskii.kmmtest.android.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mikhailovskii.kmmtest.entity.LoginData
import com.mikhailovskii.kmmtest.state.LoginResultState
import com.mikhailovskii.kmmtest.usecase.LoginUseCase

class LoginViewModel : BaseViewModel() {

    private val loginUseCase: LoginUseCase = LoginUseCase()

    private val _loginResultLiveData = MutableLiveData<LoginResultState>()
    internal val loginResultLiveData: LiveData<LoginResultState> = _loginResultLiveData

    internal fun loginUser(login: String, password: String) {
        loginUseCase(LoginData(login, password)) {
            it.either(::handleFailure) {
                _loginResultLiveData.value = LoginResultState.LoginSuccess
            }
        }
    }

}