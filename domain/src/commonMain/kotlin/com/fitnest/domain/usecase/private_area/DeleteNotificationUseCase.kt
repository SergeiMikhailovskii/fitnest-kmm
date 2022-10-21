package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.entity.request.DeleteNotificationRequest
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.extension.mapError
import com.fitnest.domain.repository.NetworkRepository

class DeleteNotificationUseCase(
    private val repository: NetworkRepository,
    private val exceptionHandler: ExceptionHandler
) {

    suspend operator fun invoke(request: DeleteNotificationRequest) = runCatching {
        repository.deleteNotification(request)
    }.mapError(exceptionHandler::getError)
}
