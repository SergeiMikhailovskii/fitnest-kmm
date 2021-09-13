package com.mikhailovskii.kmmtest.service

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class NetworkService {

    val httpClient = HttpClient {
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