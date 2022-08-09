package com.fitnest.domain.usecase

import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.domain.repository.NetworkRepository

class GenerateTokenUseCase(private val repository: NetworkRepository) : UseCase<BaseResponse>() {

    override suspend fun run() = repository.generateToken()
}
