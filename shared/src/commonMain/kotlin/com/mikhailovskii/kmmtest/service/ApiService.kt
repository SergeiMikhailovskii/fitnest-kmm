package com.mikhailovskii.kmmtest.service

import com.mikhailovskii.kmmtest.entity.LoginData
import io.ktor.client.call.*

object ApiService {

    private val networkService = NetworkService()

    suspend fun loginUser(data: LoginData) {
        val url = "http://10.0.2.2:8080/auth/login"
        val result = networkService.sendData(url, data).receive<String>()
    }

}