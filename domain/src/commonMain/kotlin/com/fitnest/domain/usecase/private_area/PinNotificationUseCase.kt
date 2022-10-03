package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.entity.request.PinNotificationRequest
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.repository.NetworkRepository

class PinNotificationUseCase(
    private val repository: NetworkRepository,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke(request: PinNotificationRequest) = kotlin.runCatching {
        repository.pinNotification(request)
    }.mapError(exceptionHandler::getError)
}
