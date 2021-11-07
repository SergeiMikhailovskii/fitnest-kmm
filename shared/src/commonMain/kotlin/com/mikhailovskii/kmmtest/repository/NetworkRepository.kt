package com.mikhailovskii.kmmtest.repository

import com.fitnest.domain.entity.LoginData
import com.fitnest.domain.repository.NetworkRepository
import com.fitnest.domain.service.NetworkService
import com.mikhailovskii.kmmtest.network.Endpoints
import org.kodein.di.DI
import org.kodein.di.instance

class NetworkRepository(val di: DI) : NetworkRepository {

    private val networkService: NetworkService by di.instance()

    override suspend fun loginUser(data: LoginData) {
    }

    override suspend fun generateToken() = networkService.getData(Endpoints.Main.name)

}