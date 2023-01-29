package com.fitnest.exception

import com.fitnest.domain.functional.Failure
import com.fitnest.util.Util
import io.ktor.client.plugins.ResponseException
import io.ktor.http.HttpStatusCode
import kotlin.test.Test
import kotlin.test.assertEquals

class GeneralExceptionHandlerTest {

    private val exceptionHandler by lazy {
        GeneralExceptionHandler()
    }

    @Test
    fun mapResponseException() {
        val output = exceptionHandler.getError(
            ResponseException(
                Util.getHttpResponse(HttpStatusCode.InternalServerError),
                ""
            )
        )
        assertEquals(Failure.ServerError(500), output)
    }

    @Test
    fun mapValidationError() {
        val testInput = Failure.ValidationErrors(
            listOf(Failure.ValidationError("testField", "testMessage"))
        )
        val output = exceptionHandler.getError(testInput)
        assertEquals(testInput, output)
    }

    @Test
    fun mapUnsupportedError() {
        val output = exceptionHandler.getError(IllegalStateException("test"))
        assertEquals(Failure.Unknown, output)
    }
}
