package com.fitnest.android.screen.privateArea.activityTracker.input

import com.fitnest.android.base.Route
import com.fitnest.android.screen.private_area.activity_tracker.ActivityTrackerViewMapper
import com.fitnest.android.screen.private_area.activity_tracker.input.ActivityInputScreenData
import com.fitnest.android.screen.private_area.activity_tracker.input.ActivityInputViewModel
import com.fitnest.domain.entity.request.AddActivityRequest
import com.fitnest.domain.enum.ActivityType
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.privateArea.AddActivityUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ActivityInputViewModelTest {

    private val failures = mutableListOf<Failure>()
    private val progresses = mutableListOf<Boolean>()
    private val routes = mutableListOf<Route>()
    private val states = mutableListOf<ActivityInputScreenData>()

    private val testDispatcher = StandardTestDispatcher()

    @MockK
    internal lateinit var viewMapper: ActivityTrackerViewMapper

    @MockK
    internal lateinit var addActivityUseCase: AddActivityUseCase

    @BeforeEach
    fun setUpMocks() = MockKAnnotations.init(this)

    @BeforeEach
    fun setUpDispatchers() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun resetDispatchers() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    private val viewModel by lazy { ActivityInputViewModel(viewMapper, addActivityUseCase) }

    @Test
    fun setCurrentActiveTab() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)
        val statesJob = viewModel.screenDataFlow.onEach(states::add).launchIn(this)

        viewModel.setCurrentActiveTab(ActivityType.WATER)
        advanceUntilIdle()
        viewModel.setCurrentActiveTab(ActivityType.STEPS)
        advanceUntilIdle()

        failureJob.cancel()
        progressesJob.cancel()
        routesJob.cancel()
        statesJob.cancel()

        assertEquals(
            listOf(
                ActivityInputScreenData(activityType = ActivityType.WATER),
                ActivityInputScreenData(activityType = ActivityType.STEPS),
            ), states
        )
        assertTrue(failures.isEmpty())
        assertTrue(progresses.isEmpty())
        assertTrue(routes.isEmpty())
    }

    @Test
    fun setValue() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)

        viewModel.setValue(100)
        advanceUntilIdle()

        failureJob.cancel()
        progressesJob.cancel()
        routesJob.cancel()

        assertTrue(failures.isEmpty())
        assertTrue(progresses.isEmpty())
        assertTrue(routes.isEmpty())
    }

    @Test
    fun submitEmptyActivity() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)

        viewModel.submitActivity()
        advanceUntilIdle()

        failureJob.cancel()
        progressesJob.cancel()
        routesJob.cancel()

        assertTrue(failures.isEmpty())
        assertEquals(listOf(true, false), progresses)
        assertEquals(listOf(Route.DismissBottomSheet), routes)
    }

    @Test
    fun submitActivitySuccess() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)

        val request = AddActivityRequest(0, ActivityType.STEPS)

        every { viewMapper.mapActivityInputToRequest(any()) } returns request
        coEvery { addActivityUseCase(any()) } answers { Result.success(Unit) }

        viewModel.setValue(100)
        viewModel.submitActivity()
        advanceUntilIdle()

        failureJob.cancel()
        progressesJob.cancel()
        routesJob.cancel()

        assertTrue(failures.isEmpty())
        assertEquals(listOf(true, false), progresses)
        assertEquals(listOf(Route.DismissBottomSheet), routes)
        coVerify(exactly = 1) { addActivityUseCase(request) }
        coVerify(exactly = 1) { addActivityUseCase(any()) }
    }

    @Test
    fun submitActivityFailure() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)

        val request = AddActivityRequest(0, ActivityType.STEPS)

        every { viewMapper.mapActivityInputToRequest(any()) } returns request
        coEvery { addActivityUseCase(any()) } answers { Result.failure(Failure.ServerError(500)) }

        viewModel.setValue(100)
        viewModel.submitActivity()
        advanceUntilIdle()

        failureJob.cancel()
        progressesJob.cancel()
        routesJob.cancel()

        assertEquals(listOf(Failure.ServerError(500)), failures)
        assertEquals(listOf(true, false), progresses)
        coVerify(exactly = 1) { addActivityUseCase(request) }
        coVerify(exactly = 1) { addActivityUseCase(any()) }
    }
}