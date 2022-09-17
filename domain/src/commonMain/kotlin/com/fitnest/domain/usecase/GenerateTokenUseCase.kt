package com.fitnest.domain.usecase

import com.fitnest.domain.repository.NetworkRepository

class GenerateTokenUseCase(private val repository: NetworkRepository) {

    suspend operator fun invoke() = runCatching { repository.generateToken() }
}
