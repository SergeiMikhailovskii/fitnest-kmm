package com.mikhailovskii.kmmtest.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikhailovskii.kmmtest.entity.LoginData
import com.mikhailovskii.kmmtest.state.LoginResultState
import com.mikhailovskii.kmmtest.usecase.LoginUseCase

class LoginViewModel : ViewModel() {

    private val _loginResultLiveData = MutableLiveData<LoginResultState>()
    internal val loginResultLiveData: LiveData<LoginResultState> = _loginResultLiveData

    internal fun loginUser(login: String, password: String) {
        LoginUseCase().save(LoginData(login, password))
        _loginResultLiveData.value = LoginResultState.LoginSuccess
    }

}