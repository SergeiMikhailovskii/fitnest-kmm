package com.fitnest.domain.usecase.privateArea

import com.fitnest.domain.repository.DatabaseRepository

class ClearCacheUseCase(
    private val databaseRepository: DatabaseRepository
) {

    operator fun invoke() = runCatching {
        databaseRepository.deleteDashboard()
        databaseRepository.deleteActivityTrackerResponse()
        databaseRepository.deleteProfileResponse()
    }
}
