package com.fitnest.domain.repository

import com.fitnest.domain.entity.cache.DashboardCacheModel

interface DatabaseRepository {

    fun getDashboard(): DashboardCacheModel?

    fun saveDashboardResponse(response: DashboardCacheModel)
}
