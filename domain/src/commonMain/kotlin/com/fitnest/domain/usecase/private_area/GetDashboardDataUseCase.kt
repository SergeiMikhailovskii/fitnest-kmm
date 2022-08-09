package com.fitnest.domain.usecase.private_area

import com.fitnest.domain.entity.response.DashboardResponse
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement

class GetDashboardDataUseCase(
    private val networkRepository: NetworkRepository,
    private val json: Json
) {

    suspend operator fun invoke() = runCatching {
        val jsonData = networkRepository.getDashboardData()
        json.decodeFromJsonElement<DashboardResponse>(jsonData.data)
    }
}
