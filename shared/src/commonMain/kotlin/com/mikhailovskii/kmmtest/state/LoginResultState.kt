package com.mikhailovskii.kmmtest.state

sealed class LoginResultState {
    object LoginSuccess: LoginResultState()
    object LoginFailure: LoginResultState()
}
