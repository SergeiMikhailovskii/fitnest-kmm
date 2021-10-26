package com.mikhailovskii.kmmtest.service

import com.fitnest.domain.functional.Either
import com.fitnest.domain.functional.Failure
import com.mikhailovskii.kmmtest.cookie.CookiesStorage
import com.mikhailovskii.kmmtest.network.Endpoints
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.cookies.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.kodein.di.DI

class NetworkService(val di: DI) {

    private val httpClient = HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(HttpCookies) {
            storage = CookiesStorage(di)
        }
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun <T : Any> sendData(url: String, data: T): HttpResponse = httpClient.post(url) {
        contentType(ContentType.Application.Json)
        body = data
    }

    suspend fun fetchData(path: String): Either<Failure, String> {
        val url = "${Endpoints.BASE_URL}${path}"
        try {
            val response: HttpResponse = httpClient.get(url) {
                contentType(ContentType.Application.Json)
            }
            val responseStr = response.receive<String>()
            return Either.Right(responseStr)
        } catch (e: ClientRequestException) {
            return Either.Left(Failure.ServerError(e.response.status.value))
        }
    }
}