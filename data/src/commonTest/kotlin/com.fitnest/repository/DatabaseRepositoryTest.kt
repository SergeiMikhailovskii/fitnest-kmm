package com.fitnest.repository

import com.fitnest.FitnestDatabase
import com.fitnest.domain.entity.cache.ActivityTrackerCacheModel
import com.fitnest.util.testDriver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class DatabaseRepositoryTest {

    private val database by lazy {
        FitnestDatabase(testDriver())
    }

    private val repository by lazy<com.fitnest.domain.repository.DatabaseRepository> {
        DatabaseRepository(database)
    }

    @Test
    fun saveActivityTrackerResponse() = runTest {
        val time = Clock.System.now().toEpochMilliseconds()
        val activityProgressWidget = "{\"progresses\":[{\"date\":\"2023-02-26\",\"total\":1000}]}"
        val latestActivityWidget =
            "{\"activities\":[{\"id\":1,\"amount\":1000,\"time\":\"2023-02-26T00:00\"}]}"
        val todayTargetWidget = "{\"water_intake\":1000,\"steps\":1000}"

        val input = ActivityTrackerCacheModel(
            activityProgressWidget = activityProgressWidget,
            latestActivityWidget = latestActivityWidget,
            todayTargetWidget = todayTargetWidget,
            timeAt = time
        )

        database.activityTrackerQueries.deleteActivityTracker()
        assertTrue {
            database.activityTrackerQueries.getActivityTracker().executeAsList().isEmpty()
        }

        repository.saveActivityTrackerResponse(input)

        val records = database.activityTrackerQueries.getActivityTracker().executeAsList()
        assertTrue { records.size == 1 }

        val record = records.first()
        assertEquals(time, record.timeAt)
        assertEquals(activityProgressWidget, record.activityProgressWidget)
        assertEquals(latestActivityWidget, record.latestActivityWidget)
        assertEquals(todayTargetWidget, record.todayTargetWidget)
    }
}
