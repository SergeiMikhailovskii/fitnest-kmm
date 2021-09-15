package com.mikhailovskii.kmmtest.service

import com.mikhailovskii.kmmtest.cookie.CookiesStorage
import io.ktor.client.*
import io.ktor.client.features.cookies.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class NetworkService {

    private val httpClient = HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(HttpCookies) {
            storage = CookiesStorage()
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
}