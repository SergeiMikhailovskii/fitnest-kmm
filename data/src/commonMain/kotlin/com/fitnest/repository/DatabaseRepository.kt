package com.fitnest.repository

import com.fitnest.FitnestDatabase
import com.fitnest.domain.entity.cache.ActivityTrackerCacheModel
import com.fitnest.domain.entity.cache.DashboardCacheModel
import com.fitnest.domain.entity.cache.ProfileCacheModel
import com.fitnest.domain.repository.DatabaseRepository

class DatabaseRepository(private val database: FitnestDatabase) : DatabaseRepository {
    override fun getDashboard() = run {
        val row = database.dashboardQueries.getDashboard().executeAsOneOrNull()

        row?.let {
            DashboardCacheModel(
                activityStatusWidget = it.activityStatusWidget,
                bmiWidget = it.bmiWidget,
                headerWidget = it.headerWidget,
                latestWorkoutWidget = it.latestWorkoutWidget,
                todayTargetWidget = it.todayTargetWidget,
                timeAt = it.timeAt
            )
        }
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

    override fun deleteDashboard() {
        database.dashboardQueries.deleteDashboard()
    }

    override fun getActivityTracker() = run {
        val row = database.activityTrackerQueries.getActivityTracker().executeAsOneOrNull()

        row?.let {
            ActivityTrackerCacheModel(
                activityProgressWidget = it.activityProgressWidget,
                latestActivityWidget = it.latestActivityWidget,
                todayTargetWidget = it.todayTargetWidget,
                timeAt = it.timeAt
            )
        }
    }

    override fun saveActivityTrackerResponse(response: ActivityTrackerCacheModel) {
        database.activityTrackerQueries.deleteActivityTracker()
        database.activityTrackerQueries.saveActivityTracker(
            response.activityProgressWidget,
            response.latestActivityWidget,
            response.todayTargetWidget,
            response.timeAt
        )
    }

    override fun deleteActivityTrackerResponse() {
        database.activityTrackerQueries.deleteActivityTracker()
    }

    override fun getProfile() = run {
        val row = database.profileQueries.getProfile().executeAsOneOrNull()

        row?.let {
            ProfileCacheModel(
                profileInfoWidget = it.profileInfoWidget,
                timeAt = it.timeAt
            )
        }
    }

    override fun saveProfileResponse(response: ProfileCacheModel) {
        database.profileQueries.deleteProfile()
        database.profileQueries.saveProfile(
            response.profileInfoWidget,
            response.timeAt
        )
    }

    override fun deleteProfileResponse() {
        database.profileQueries.deleteProfile()
    }
}
