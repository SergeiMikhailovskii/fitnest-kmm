package com.fitnest.util

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.util.InternalAPI
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.ByteReadChannel
import kotlin.coroutines.EmptyCoroutineContext

object Util {
    fun getHttpResponse(statusCode: HttpStatusCode) = object : HttpResponse() {
        override val call: HttpClientCall = HttpClientCall(HttpClient {
        })

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
}