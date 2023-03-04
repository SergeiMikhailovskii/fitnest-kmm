package com.fitnest.domain.usecase.privateArea

import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.domain.entity.cache.ActivityTrackerCacheModel
import com.fitnest.domain.entity.request.AddActivityRequest
import com.fitnest.domain.enum.ActivityType
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.mapper.db.ActivityTrackerResponseToCacheMapperAlias
import com.fitnest.domain.repository.DatabaseRepository
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class AddActivityUseCaseTest : TestsWithMocks() {

    override fun setUpMocks() = injectMocks(mocker)

    @Mock
    internal lateinit var repository: NetworkRepository

    @Mock
    internal lateinit var dbRepository: DatabaseRepository

    @Mock
    internal lateinit var responseToCacheMapper: ActivityTrackerResponseToCacheMapperAlias

    @Mock
    internal lateinit var exceptionHandler: ExceptionHandler

    private val useCase by lazy {
        AddActivityUseCase(repository, dbRepository, Json, responseToCacheMapper, exceptionHandler)
    }

    @Test
    fun addActivitySuccess() = runTest {
        everySuspending { repository.addActivity(isAny()) } returns BaseResponse()
        everySuspending { repository.getActivityTrackerPage() } returns BaseResponse(
            data = JsonObject(
                mapOf(
                    "widgets" to JsonObject(
                        mapOf(
                            "ACTIVITY_PROGRESS_WIDGET" to JsonNull,
                            "LATEST_ACTIVITY_WIDGET" to JsonNull,
                            "TODAY_TARGET_WIDGET" to JsonNull
                        )
                    )
                )
            )
        )
        every { responseToCacheMapper.map(isAny()) } returns ActivityTrackerCacheModel(timeAt = 0)
        every { dbRepository.saveActivityTrackerResponse(isAny()) } returns Unit
        val useCaseOutput = useCase(AddActivityRequest(0, ActivityType.STEPS)).getOrThrow()
        advanceUntilIdle()
        assertEquals(Unit, useCaseOutput)
    }

    @Test
    fun addActivitySendDataFailure() = runTest {
        everySuspending { repository.addActivity(isAny()) } runs {
            error("Test failure")
        }
        every { exceptionHandler.getError(isAny()) } returns Failure.ServerError(500)
        val useCaseOutput = useCase(AddActivityRequest(0, ActivityType.STEPS))
        advanceUntilIdle()
        assertEquals(Result.failure(Failure.ServerError(500)), useCaseOutput)
    }

    @Test
    fun addActivityGetDataFailure() = runTest {
        everySuspending { repository.addActivity(isAny()) } returns BaseResponse()
        everySuspending { repository.getActivityTrackerPage() } runs {
            error("Test failure")
        }
        every { exceptionHandler.getError(isAny()) } returns Failure.ServerError(500)
        val useCaseOutput = useCase(AddActivityRequest(0, ActivityType.STEPS))
        advanceUntilIdle()
        assertEquals(Result.failure(Failure.ServerError(500)), useCaseOutput)
    }

    @Test
    fun addActivitySaveDataFailure() = runTest {
        everySuspending { repository.addActivity(isAny()) } returns BaseResponse()
        everySuspending { repository.getActivityTrackerPage() } returns BaseResponse(
            data = JsonObject(
                mapOf(
                    "widgets" to JsonObject(
                        mapOf(
                            "ACTIVITY_PROGRESS_WIDGET" to JsonNull,
                            "LATEST_ACTIVITY_WIDGET" to JsonNull,
                            "TODAY_TARGET_WIDGET" to JsonNull
                        )
                    )
                )
            )
        )
        every { responseToCacheMapper.map(isAny()) } returns ActivityTrackerCacheModel(timeAt = 0)
        every { dbRepository.saveActivityTrackerResponse(isAny()) } runs {
            error("Test failure")
        }
        every { exceptionHandler.getError(isAny()) } returns Failure.Unknown
        val useCaseOutput = useCase(AddActivityRequest(0, ActivityType.STEPS))
        advanceUntilIdle()
        assertEquals(Result.failure(Failure.Unknown), useCaseOutput)
    }
}
