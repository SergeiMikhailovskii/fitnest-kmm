package com.fitnest.domain.usecase.splash

import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.domain.exception.ExceptionHandler
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.repository.NetworkRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class GenerateTokenUseCaseTest : TestsWithMocks() {

    @Mock
    internal lateinit var repository: NetworkRepository

    @Mock
    internal lateinit var exceptionHandler: ExceptionHandler

    private val useCase by lazy {
        GenerateTokenUseCase(repository, exceptionHandler)
    }

    override fun setUpMocks() = injectMocks(mocker)

    @Test
    fun generateTokenSuccess() = runTest {
        everySuspending { repository.generateToken() } returns BaseResponse()
        val useCaseOutput = useCase().getOrThrow()
        assertEquals(Unit, useCaseOutput)
    }

    @Test
    fun generateTokenFailure() = runTest {
        val testFailure = Failure.ServerError(500)
        everySuspending { repository.generateToken() } runs { throw testFailure }
        every { exceptionHandler.getError(isAny()) } returns testFailure
        val useCaseResult = useCase()
        val exception = assertFailsWith<Failure.ServerError> { useCaseResult.getOrThrow() }
        assertEquals(testFailure, exception)
    }
}
