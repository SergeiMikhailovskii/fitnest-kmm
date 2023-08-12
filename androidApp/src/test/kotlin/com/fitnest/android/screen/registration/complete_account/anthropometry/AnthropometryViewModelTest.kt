package com.fitnest.android.screen.registration.complete_account.anthropometry

import com.fitnest.domain.functional.Failure
import com.fitnest.presentation.navigation.Route
import com.fitnest.presentation.screen.registration.completeAccount.anthropometry.AnthropometryEventsBus
import com.fitnest.presentation.screen.registration.completeAccount.anthropometry.AnthropometryViewModel
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
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AnthropometryViewModelTest {

    private val failures = mutableListOf<Failure>()
    private val routes = mutableListOf<Route>()

    private val testDispatcher = StandardTestDispatcher()

    @MockK
    internal lateinit var anthropometryEventsBus: AnthropometryEventsBus

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
        AnthropometryViewModel(
            anthropometryEventsBus = anthropometryEventsBus
        )
    }

    @Test
    fun submitValue() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)
        coEvery { anthropometryEventsBus.sendEvent(any()) } returns Unit

        viewModel.submitValue()
        advanceUntilIdle()

        failureJob.cancel()
        routesJob.cancel()

        Assertions.assertTrue(failures.isEmpty())
        Assertions.assertEquals(listOf(Route.DismissBottomSheet), routes)
    }

    @Test
    fun dismiss() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)

        viewModel.dismiss()
        advanceUntilIdle()

        failureJob.cancel()
        routesJob.cancel()

        Assertions.assertTrue(failures.isEmpty())
        Assertions.assertEquals(listOf(Route.DismissBottomSheet), routes)
    }
}