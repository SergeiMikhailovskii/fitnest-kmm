package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.repository.NetworkRepository

class DeactivateNotificationsUseCase(
    private val repository: NetworkRepository,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke(ids: List<Int>?) = runCatching {
        repository.deactivateNotifications(ids)
    }.mapError(exceptionHandler::getError)
}
