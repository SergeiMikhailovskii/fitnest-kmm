package com.fitnest.android.screen.privateArea.activityTracker

import com.fitnest.presentation.screen.privateArea.activityTracker.ActivityTrackerViewMapper
import com.fitnest.presentation.screen.privateArea.activityTracker.ActivityTrackerViewModel
import com.fitnest.presentation.screen.privateArea.activityTracker.data.ActivityTrackerScreenData
import com.fitnest.domain.entity.request.DeleteActivityRequest
import com.fitnest.domain.entity.response.ActivityTrackerPageResponse
import com.fitnest.domain.enum.ActivityType
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.privateArea.DeleteActivityUseCase
import com.fitnest.domain.usecase.privateArea.GetActivityTrackerPageUseCase
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import io.mockk.MockKAnnotations
import io.mockk.coEvery
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
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ActivityTrackerViewModelTest {

    private val failures = mutableListOf<Failure>()
    private val progresses = mutableListOf<Boolean>()
    private val routes = mutableListOf<com.fitnest.presentation.navigation.Route>()
    private val states = mutableListOf<ActivityTrackerScreenData>()

    private val testDispatcher = StandardTestDispatcher()

    @MockK
    internal lateinit var viewMapper: ActivityTrackerViewMapper

    @MockK
    internal lateinit var getActivityTrackerPageUseCase: GetActivityTrackerPageUseCase

    @MockK
    internal lateinit var deleteActivityUseCase: DeleteActivityUseCase

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

    private val viewModel by lazy {
        ActivityTrackerViewModel(
            getActivityTrackerPageUseCase = getActivityTrackerPageUseCase,
            deleteActivityUseCase = deleteActivityUseCase,
            viewMapper = viewMapper
        )
    }

    @Test
    fun getInitialInfoSuccess() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)
        val statesJob = viewModel.screenDataFlow.onEach(states::add).launchIn(this)

        val response = ActivityTrackerPageResponse.ActivityTrackerWidgets()
        val screenData = ActivityTrackerScreenData(
            activityProgressWidget = ActivityTrackerScreenData.ActivityProgressWidget()
        )
        coEvery { getActivityTrackerPageUseCase() } returns Result.success(response)
        every { viewMapper.mapWidgetsToScreenData(response) } returns screenData

        viewModel.getInitialInfo()
        advanceUntilIdle()

        failureJob.cancel()
        progressesJob.cancel()
        routesJob.cancel()
        statesJob.cancel()

        Assertions.assertEquals(listOf(screenData), states)
        Assertions.assertTrue(failures.isEmpty())
        Assertions.assertEquals(listOf(true, false), progresses)
        Assertions.assertTrue(routes.isEmpty())
    }

    @Test
    fun getInitialInfoFailure() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)

        coEvery { getActivityTrackerPageUseCase() } returns Result.failure(Failure.ServerError(500))

        viewModel.getInitialInfo()
        advanceUntilIdle()

        failureJob.cancel()
        progressesJob.cancel()
        routesJob.cancel()

        Assertions.assertEquals(listOf(Failure.ServerError(500)), failures)
        Assertions.assertEquals(listOf(true, false), progresses)
        Assertions.assertTrue(routes.isEmpty())
    }

    @Test
    fun deleteActivitySuccess() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)
        val statesJob = viewModel.screenDataFlow.onEach(states::add).launchIn(this)

        val mockActivity = ActivityTrackerScreenData.Activity(
            0,
            StringDesc.Raw(""),
            "",
            ActivityType.STEPS,
            ""
        )
        val mockRequest = DeleteActivityRequest(0, ActivityType.STEPS)
        val mockResponse = ActivityTrackerPageResponse.ActivityTrackerWidgets()
        val mockScreenData = ActivityTrackerScreenData(
            activityProgressWidget = ActivityTrackerScreenData.ActivityProgressWidget()
        )

        every { viewMapper.mapActivityToDeleteActivityRequest(mockActivity) } returns mockRequest
        coEvery { deleteActivityUseCase(mockRequest) } returns Result.success(mockResponse)
        every { viewMapper.mapWidgetsToScreenData(mockResponse) } returns mockScreenData

        viewModel.deleteActivity(mockActivity)
        advanceUntilIdle()

        failureJob.cancel()
        progressesJob.cancel()
        routesJob.cancel()
        statesJob.cancel()

        Assertions.assertEquals(listOf(mockScreenData), states)
        Assertions.assertTrue(failures.isEmpty())
        Assertions.assertEquals(listOf(true, false), progresses)
        Assertions.assertTrue(routes.isEmpty())
    }

    @Test
    fun deleteActivityFailure() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)

        val mockActivity = ActivityTrackerScreenData.Activity(
            0,
            StringDesc.Raw(""),
            "",
            ActivityType.STEPS,
            ""
        )
        val mockRequest = DeleteActivityRequest(0, ActivityType.STEPS)

        every { viewMapper.mapActivityToDeleteActivityRequest(mockActivity) } returns mockRequest
        coEvery { deleteActivityUseCase(mockRequest) } returns Result.failure(
            Failure.ServerError(500)
        )

        viewModel.deleteActivity(mockActivity)
        advanceUntilIdle()

        failureJob.cancel()
        progressesJob.cancel()
        routesJob.cancel()

        Assertions.assertEquals(listOf(Failure.ServerError(500)), failures)
        Assertions.assertEquals(listOf(true, false), progresses)
        Assertions.assertTrue(routes.isEmpty())
    }

    @Test
    fun navigateNext() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)

        viewModel.openActivityInputBottomSheet()
        advanceUntilIdle()

        failureJob.cancel()
        progressesJob.cancel()
        routesJob.cancel()

        Assertions.assertEquals(emptyList<Failure>(), failures)
        Assertions.assertEquals(emptyList<Boolean>(), progresses)
        Assertions.assertEquals(
            listOf(com.fitnest.presentation.navigation.Route.PrivateArea.Tracker.ActivityInputBottomSheet),
            routes
        )
    }
}
