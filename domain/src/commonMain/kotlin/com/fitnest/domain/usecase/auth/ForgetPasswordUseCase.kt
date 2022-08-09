package com.fitnest.domain.usecase.auth

import com.fitnest.domain.entity.request.ForgetPasswordRequest
import com.fitnest.domain.repository.NetworkRepository

class ForgetPasswordUseCase(
    private val repository: NetworkRepository,
) {

    suspend operator fun invoke(request: ForgetPasswordRequest) = runCatching {
        repository.forgetPassword(request)
    }
}
