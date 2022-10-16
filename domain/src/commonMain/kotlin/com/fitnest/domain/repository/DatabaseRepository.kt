package com.fitnest.domain.repository

import com.fitnest.domain.entity.cache.DashboardCacheModel

interface DatabaseRepository {

    fun getDashboard()

    fun saveDashboardResponse(response: DashboardCacheModel)
}
