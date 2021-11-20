package com.fitnest.repository

import com.fitnest.domain.entity.LoginData
import com.fitnest.domain.repository.NetworkRepository
import com.fitnest.domain.service.NetworkService
import com.fitnest.network.Endpoints
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.kodein.di.DI
import org.kodein.di.instance

@ExperimentalSerializationApi
class NetworkRepository(val di: DI) : NetworkRepository {

    private val networkService: NetworkService by di.instance()

    override suspend fun loginUser(data: LoginData) {
    }

    override suspend fun generateToken() = networkService.getData(Endpoints.Main.name)

    override suspend fun getOnboardingStep() = networkService.getData(Endpoints.Onboarding.name)
        .map {
            it?.data?.jsonObject?.get("step")?.jsonPrimitive?.content ?: ""
        }

}