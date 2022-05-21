package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.domain.repository.NetworkRepository
import com.fitnest.domain.usecase.UseCaseResult

class GetDashboardDataUseCase(
    private val networkRepository: NetworkRepository
) : UseCaseResult<BaseResponse>() {

    override suspend fun run() = runCatching {
        networkRepository.getDashboardData()
    }

}