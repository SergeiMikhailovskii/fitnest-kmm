package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.repository.NetworkRepository

class GetProfilePageUseCase internal constructor(
    private val networkRepository: NetworkRepository
) {

    suspend operator fun invoke() = runCatching {
        networkRepository.getProfilePage()
    }.map {
        Unit
    }

}