package com.fitnest.service

import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.util.Response
import com.fitnest.util.Util
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class NetworkServiceTest {

    @Test
    fun getDataSuccess() = runTest {
        val service = NetworkService(
            Util.getHttpClient(
                Response.Success(
                    content = """{
                |    "data": null,
                |    "errors": null,
                |    "flow": "/onboarding"
                |}
            """.trimMargin()
                )
            )
        )
        val response = service.getData("flow")
        assertEquals(BaseResponse(flow = "/onboarding"), response)
    }

    @Test
    fun getDataFailure() = runTest {
        val service = NetworkService(Util.getHttpClient(Response.Error()))
        assertFailsWith<ResponseException> { service.getData("flow") }
    }
}
