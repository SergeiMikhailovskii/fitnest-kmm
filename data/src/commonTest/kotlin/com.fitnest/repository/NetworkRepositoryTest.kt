package com.fitnest.repository

import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.domain.service.NetworkService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonPrimitive
import org.kodein.mock.Mock
import org.kodein.mock.tests.TestsWithMocks
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class NetworkRepositoryTest : TestsWithMocks() {

    @Mock
    internal lateinit var service: NetworkService

    private val repository: com.fitnest.domain.repository.NetworkRepository by lazy {
        NetworkRepository(service)
    }

    override fun setUpMocks() = injectMocks(mocker)

    @Test
    fun generateTokenSuccess() = runTest {
        val testResponse = BaseResponse(data = JsonPrimitive("test"))
        everySuspending { service.getData("flow") } returns testResponse
        val output = repository.generateToken()
        assertEquals(testResponse, output)
    }

    @Test
    fun generateTokenFailure() = runTest {
        val testMessage = "test failure"
        everySuspending { service.getData("flow") } runs { error(testMessage) }
        val exception = assertFailsWith<IllegalStateException> { repository.generateToken() }
        assertEquals(testMessage, exception.message)
    }
}