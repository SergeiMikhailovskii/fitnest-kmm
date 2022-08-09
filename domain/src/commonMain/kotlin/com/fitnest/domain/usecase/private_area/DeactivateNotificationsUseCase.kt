package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.repository.NetworkRepository

class DeactivateNotificationsUseCase(
    private val repository: NetworkRepository
) {

    suspend operator fun invoke(ids: List<Int>?) = repository.deactivateNotifications(ids)
}
