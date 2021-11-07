package com.fitnest.domain.usecase

import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.domain.repository.NetworkRepository
import org.kodein.di.DI
import org.kodein.di.instance

class GenerateTokenUseCase(val di: DI) : UseCase<BaseResponse>() {

    private val repository: NetworkRepository by di.instance()

    override suspend fun run() = repository.generateToken()

}