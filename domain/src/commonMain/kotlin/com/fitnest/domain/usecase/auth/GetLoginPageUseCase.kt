package com.fitnest.domain.usecase.auth

import com.fitnest.domain.entity.response.LoginPageResponse
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

class GetLoginPageUseCase(
    private val repository: NetworkRepository,
    private val json: Json
) {

    suspend operator fun invoke() = runCatching {
        repository.getLoginPage()
    }.map {
        json.decodeFromJsonElement<LoginPageResponse>(it.data)
    }
}
