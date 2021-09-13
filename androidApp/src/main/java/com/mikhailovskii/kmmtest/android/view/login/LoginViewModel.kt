package com.mikhailovskii.kmmtest.android.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mikhailovskii.kmmtest.android.view.BaseViewModel
import com.mikhailovskii.kmmtest.entity.LoginData
import com.mikhailovskii.kmmtest.service.ApiService
import com.mikhailovskii.kmmtest.state.LoginResultState
import com.mikhailovskii.kmmtest.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _loginResultLiveData = MutableLiveData<LoginResultState>()
    internal val loginResultLiveData: LiveData<LoginResultState> = _loginResultLiveData

    internal fun loginUser(login: String, password: String) {
//        loginUseCase(LoginData(login, password)) {
//            it.either(::handleFailure) {
//                _loginResultLiveData.value = LoginResultState.LOGIN_SUCCESS
//            }
//        }
        viewModelScope.launch(Dispatchers.IO) {
            ApiService().loginUser()
        }
    }

    internal fun dismissLoginDialog() {
        _loginResultLiveData.value = LoginResultState.LOGIN_DEFAULT
    }

}