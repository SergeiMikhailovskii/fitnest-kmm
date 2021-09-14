package com.mikhailovskii.kmmtest.service

import com.mikhailovskii.kmmtest.dispatchers.ktorScope
import com.mikhailovskii.kmmtest.entity.LoginData

object ApiService {

    private val networkService = NetworkService()

    suspend fun loginUser(data: LoginData) {
        ktorScope {
            val url = "http://10.0.2.2:8080/auth/login"
            val result = networkService.sendData(url, data)
        }
    }

}