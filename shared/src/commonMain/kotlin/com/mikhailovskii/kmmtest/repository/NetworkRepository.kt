package com.mikhailovskii.kmmtest.repository

import com.mikhailovskii.kmmtest.Either
import com.mikhailovskii.kmmtest.Failure
import com.mikhailovskii.kmmtest.entity.LoginData
import com.mikhailovskii.kmmtest.network.Endpoints
import com.mikhailovskii.kmmtest.service.NetworkService
import io.ktor.client.call.*
import org.kodein.di.DI
import org.kodein.di.instance

class NetworkRepository(val di: DI) {

    private val networkService: NetworkService by di.instance()

    suspend fun loginUser(data: LoginData) {
        val url = "https://10.0.2.2:8080/auth/login"
        val result = networkService.sendData(url, data).receive<String>()
    }

    suspend fun generateToken() = networkService.fetchData(Endpoints.Main.name)

}