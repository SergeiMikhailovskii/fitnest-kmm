package com.fitnest.util

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.json.Json
import kotlin.coroutines.EmptyCoroutineContext

object Util {
    fun getHttpResponse(statusCode: HttpStatusCode) = object : HttpResponse() {
        override val call: HttpClientCall = HttpClientCall(HttpClient {})

        @InternalAPI
        override val content = ByteReadChannel("body")
        override val coroutineContext = EmptyCoroutineContext
        override val headers = Headers.Empty
        override val requestTime = GMTDate.START
        override val responseTime = GMTDate.START
        override val status = statusCode
        override val version = HttpProtocolVersion.HTTP_1_0

        override fun toString() = ""
    }

    fun getHttpClient(response: Response): HttpClient {
        val engine = MockEngine {
            when (response) {
                is Response.Error -> respondError(response.statusCode)
                is Response.Success -> respond(
                    response.content,
                    headers = response.headers,
                    status = response.statusCode
                )
            }
        }
        return HttpClient(engine) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    }
                )
            }
        }
    }
}

sealed class Response(val statusCode: HttpStatusCode) {
    class Success(
        statusCode: HttpStatusCode = HttpStatusCode.OK,
        val content: String,
        val headers: Headers = headersOf(HttpHeaders.ContentType, "application/json")
    ) : Response(statusCode)

    class Error(statusCode: HttpStatusCode = HttpStatusCode.NotFound) : Response(statusCode)
}
