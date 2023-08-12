package com.fitnest.android.screen.registration.complete_account.screen

import com.fitnest.presentation.screen.registration.completeAccount.anthropometry.AnthropometryEventsBus
import com.fitnest.domain.entity.GetRegistrationResponseData
import com.fitnest.domain.entity.RegistrationScreenState
import com.fitnest.domain.entity.RegistrationStepValidationSchema
import com.fitnest.domain.entity.request.CompleteAccountStepRequest
import com.fitnest.domain.enum.SexType
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.usecase.registration.SubmitRegistrationStepAndGetNextUseCase
import com.fitnest.domain.usecase.validation.CompleteAccountRegistrationValidationUseCase
import com.fitnest.presentation.screen.registration.completeAccount.screen.CompleteAccountRegistrationScreenData
import com.fitnest.presentation.screen.registration.completeAccount.screen.CompleteAccountRegistrationViewMapper
import com.fitnest.presentation.screen.registration.completeAccount.screen.CompleteAccountRegistrationViewModel
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
import java.util.Date

@OptIn(ExperimentalCoroutinesApi::class)
class CompleteAccountRegistrationViewModelTest {

    private val failures = mutableListOf<Failure>()
    private val progresses = mutableListOf<Boolean>()
    private val routes = mutableListOf<com.fitnest.presentation.navigation.Route>()
    private val states = mutableListOf<CompleteAccountRegistrationScreenData>()

    private val testDispatcher = StandardTestDispatcher()

    @MockK
    internal lateinit var anthropometryEventsBus: AnthropometryEventsBus

    @MockK(relaxed = true)
    internal lateinit var registrationScreenState: RegistrationScreenState

    @MockK
    internal lateinit var viewMapper: CompleteAccountRegistrationViewMapper

    @MockK
    internal lateinit var validator: CompleteAccountRegistrationValidationUseCase

    @MockK
    internal lateinit var submitRegistrationStepAndGetNextUseCase: SubmitRegistrationStepAndGetNextUseCase

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
        CompleteAccountRegistrationViewModel(
            anthropometryEventsBus = anthropometryEventsBus,
            registrationScreenState = registrationScreenState,
            viewMapper = viewMapper,
            validator = validator,
            submitRegistrationStepAndGetNextUseCase = submitRegistrationStepAndGetNextUseCase
        )
    }

    @BeforeEach
    fun mockEventBus() {
        coEvery { anthropometryEventsBus.subscribe(any()) } returns Unit
    }

    @Test
    fun saveSex() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)
        val statesJob = viewModel.screenDataFlow.onEach(states::add).launchIn(this)

        val screenData = CompleteAccountRegistrationScreenData(sex = SexType.MALE)

        viewModel.saveSex(SexType.MALE)
        advanceUntilIdle()

        failureJob.cancel()
        routesJob.cancel()
        statesJob.cancel()

        Assertions.assertEquals(listOf(screenData), states)
        Assertions.assertTrue(failures.isEmpty())
        Assertions.assertTrue(routes.isEmpty())
    }

    @Test
    fun updateSexFocus() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)
        val statesJob = viewModel.screenDataFlow.onEach(states::add).launchIn(this)

        val screenData = CompleteAccountRegistrationScreenData(isSexFocused = true)

        viewModel.updateSexFocus(true)
        advanceUntilIdle()

        failureJob.cancel()
        routesJob.cancel()
        statesJob.cancel()

        Assertions.assertEquals(listOf(screenData), states)
        Assertions.assertTrue(failures.isEmpty())
        Assertions.assertTrue(routes.isEmpty())
    }

    @Test
    fun saveBirthDate() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)
        val statesJob = viewModel.screenDataFlow.onEach(states::add).launchIn(this)

        val date = Date()

        val screenData = CompleteAccountRegistrationScreenData(dateOfBirth = date)

        viewModel.saveBirthDate(date)
        advanceUntilIdle()

        failureJob.cancel()
        routesJob.cancel()
        statesJob.cancel()

        Assertions.assertEquals(listOf(screenData), states)
        Assertions.assertTrue(failures.isEmpty())
        Assertions.assertTrue(routes.isEmpty())
    }

    @Test
    fun submitRegistrationSuccess() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)

        registrationScreenState.validationSchema =
            RegistrationStepValidationSchema.CompleteAccountStepValidationSchema()

        every { viewMapper.mapScreenDataToStepRequestModel(any()) } returns
                CompleteAccountStepRequest(null, null, null, null)
        every { validator(any(), any()) } returns Result.success(Unit)
        coEvery { submitRegistrationStepAndGetNextUseCase(any()) } returns
                Result.success(GetRegistrationResponseData(step = "NEXT_STEP"))

        viewModel.submitRegistration()
        advanceUntilIdle()

        failureJob.cancel()
        routesJob.cancel()
        progressesJob.cancel()

        Assertions.assertEquals(listOf(true, false), progresses)
        Assertions.assertEquals(
            listOf(com.fitnest.presentation.navigation.Route.Registration.Step("NEXT_STEP")),
            routes
        )
        Assertions.assertTrue(failures.isEmpty())
    }

    @Test
    fun submitRegistrationLocalValidationFailure() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)

        registrationScreenState.validationSchema =
            RegistrationStepValidationSchema.CompleteAccountStepValidationSchema()

        every { viewMapper.mapScreenDataToStepRequestModel(any()) } returns
                CompleteAccountStepRequest(null, null, null, null)
        every { validator(any(), any()) } returns Result.failure(Failure.ValidationErrors(listOf()))

        viewModel.submitRegistration()
        advanceUntilIdle()

        failureJob.cancel()
        routesJob.cancel()
        progressesJob.cancel()

        Assertions.assertEquals(listOf(true, false), progresses)
        Assertions.assertEquals(listOf(Failure.ValidationErrors(listOf())), failures)
        Assertions.assertTrue(routes.isEmpty())
    }

    @Test
    fun submitRegistrationServerFailure() = runTest {
        val failureJob = viewModel.failureSharedFlow.onEach(failures::add).launchIn(this)
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)
        val progressesJob = viewModel.progressSharedFlow.onEach(progresses::add).launchIn(this)

        registrationScreenState.validationSchema =
            RegistrationStepValidationSchema.CompleteAccountStepValidationSchema()

        every { viewMapper.mapScreenDataToStepRequestModel(any()) } returns
                CompleteAccountStepRequest(null, null, null, null)
        every { validator(any(), any()) } returns Result.failure(Failure.ServerError(500))

        viewModel.submitRegistration()
        advanceUntilIdle()

        failureJob.cancel()
        routesJob.cancel()
        progressesJob.cancel()

        Assertions.assertEquals(listOf(true, false), progresses)
        Assertions.assertEquals(listOf(Failure.ServerError(500)), failures)
        Assertions.assertTrue(routes.isEmpty())
    }

    @Test
    fun openWeightBottomSheet() = runTest {
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)
        viewModel.openWeightBottomSheet()
        advanceUntilIdle()
        routesJob.cancel()

        Assertions.assertEquals(
            listOf(
                com.fitnest.presentation.navigation.Route.Registration.AnthropometryBottomSheet(
                    0,
                    200,
                    70
                )
            ), routes
        )
        Assertions.assertTrue(failures.isEmpty())
        Assertions.assertTrue(progresses.isEmpty())
    }

    @Test
    fun openHeightBottomSheet() = runTest {
        val routesJob = viewModel.routeSharedFlow.onEach(routes::add).launchIn(this)
        viewModel.openHeightBottomSheet()
        advanceUntilIdle()
        routesJob.cancel()

        Assertions.assertEquals(
            listOf(
                com.fitnest.presentation.navigation.Route.Registration.AnthropometryBottomSheet(
                    0,
                    220,
                    188
                )
            ), routes
        )
        Assertions.assertTrue(failures.isEmpty())
        Assertions.assertTrue(progresses.isEmpty())
    }
}