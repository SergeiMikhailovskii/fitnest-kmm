package com.fitnest.domain.repository

import com.fitnest.domain.entity.cache.ActivityTrackerCacheModel
import com.fitnest.domain.entity.cache.DashboardCacheModel

interface DatabaseRepository {

    fun getDashboard(): DashboardCacheModel?

    fun saveDashboardResponse(response: DashboardCacheModel)

    fun deleteDashboard()

    fun getActivityTracker(): ActivityTrackerCacheModel?

    fun saveActivityTrackerResponse(response: ActivityTrackerCacheModel)

    fun deleteActivityTrackerResponse()
}
