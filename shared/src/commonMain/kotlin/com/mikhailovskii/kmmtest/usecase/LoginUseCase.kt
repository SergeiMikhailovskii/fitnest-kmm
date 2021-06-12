package com.mikhailovskii.kmmtest.usecase

import com.mikhailovskii.kmmtest.entity.LoginData

class LoginUseCase {

    fun save(data: LoginData) {
        println("${data.login} ${data.password}")
    }

}