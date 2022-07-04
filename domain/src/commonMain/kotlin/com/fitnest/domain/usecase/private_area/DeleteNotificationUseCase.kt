package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.entity.request.DeleteNotificationRequest
import com.fitnest.domain.entity.request.PinNotificationRequest
import com.fitnest.domain.repository.NetworkRepository

class DeleteNotificationUseCase(
    private val repository: NetworkRepository
) {

    suspend operator fun invoke(request: DeleteNotificationRequest) = kotlin.runCatching {
        repository.deleteNotification(request)
    }
}