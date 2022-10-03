package com.fitnest.service

import com.fitnest.cookie.CookiesStorage
import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.domain.service.NetworkService
import com.fitnest.network.Endpoints
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.features.cookies.HttpCookies
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.kodein.di.DI

class NetworkService(val di: DI) : NetworkService {

    private val httpClient = HttpClient {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(HttpCookies) {
            storage = CookiesStorage(di)
        }
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            }
            serializer = KotlinxSerializer(json)
        }
    }

    override suspend fun <Request> sendData(path: String, data: Request?): BaseResponse {
        val url = "${Endpoints.BASE_URL}$path"
        val httpResponse: HttpResponse = httpClient.post(url) {
            contentType(ContentType.Application.Json)
            if (data != null) {
                body = data
            }
        }
        return httpResponse.receive()
    }

    override suspend fun getData(path: String): BaseResponse {
        val url = "${Endpoints.BASE_URL}$path"
        return httpClient.get(url)
    }
}
