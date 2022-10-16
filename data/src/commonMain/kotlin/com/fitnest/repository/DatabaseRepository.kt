package com.fitnest.repository

import com.fitnest.FitnestDatabase
import com.fitnest.domain.entity.cache.DashboardCacheModel
import com.fitnest.domain.repository.DatabaseRepository

class DatabaseRepository(private val database: FitnestDatabase) : DatabaseRepository {
    override fun getDashboard() {
        database.dashboardQueries.getDashboard().executeAsList()
    }

    override fun saveDashboardResponse(response: DashboardCacheModel) {
        database.dashboardQueries.deleteDashboard()
        database.dashboardQueries.saveDashboard(
            response.activityStatusWidget,
            response.bmiWidget,
            response.headerWidget,
            response.latestWorkoutWidget,
            response.todayTargetWidget,
            response.timeAt
        )
    }
}
