package com.fitnest.android.screen.splash

import com.fitnest.android.base.Route
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.splash.GenerateTokenUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
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
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SplashViewModelTest {

    private val failures = mutableListOf<Failure>()
    private val progresses = mutableListOf<Boolean>()
    private val routes = mutableListOf<Route>()

    private val testDispatcher = StandardTestDispatcher()

    @MockK
    internal lateinit var generateTokenUseCase: GenerateTokenUseCase

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
        SplashViewModel(generateTokenUseCase)
    }

    @Test
    fun generateTokenSuccess() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)

        coEvery { generateTokenUseCase() } returns Result.success(Unit)

        viewModel.generateToken()
        advanceUntilIdle()

        failureJob.cancel()
        progressesJob.cancel()
        routesJob.cancel()

        assertEquals(emptyList<Failure>(), failures)
        assertEquals(listOf(true, false), progresses)
        assertEquals(emptyList<Route>(), routes)
    }

    @Test
    fun generateTokenFailureServer500() = runTest {
        val testFailure = Failure.ServerError(500)

        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)

        coEvery { generateTokenUseCase() } returns Result.failure(testFailure)

        viewModel.generateToken()
        advanceUntilIdle()

        failureJob.cancel()
        progressesJob.cancel()
        routesJob.cancel()

        assertEquals(listOf(testFailure), failures)
        assertEquals(listOf(true, false), progresses)
        assertEquals(emptyList<Route>(), routes)
    }

    @Test
    fun navigateNext() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)

        viewModel.navigateNext()
        advanceUntilIdle()

        failureJob.cancel()
        progressesJob.cancel()
        routesJob.cancel()

        assertEquals(emptyList<Failure>(), failures)
        assertEquals(emptyList<Boolean>(), progresses)
        assertEquals(listOf(Route.Proxy()), routes)
    }
}
