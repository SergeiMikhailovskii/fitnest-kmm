package com.mikhailovskii.kmmtest.service

import com.mikhailovskii.kmmtest.dispatchers.ktorScope
import com.mikhailovskii.kmmtest.entity.LoginData

class ApiService {

    val networkService = NetworkService()

    suspend fun loginUser() {
        ktorScope {
            val url = "http://10.0.2.2:8080/auth/login"
            val result = networkService.sendData(url, LoginData("Sergei", "12345"))
            println()
        }
    }

}