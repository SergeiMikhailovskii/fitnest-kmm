package com.fitnest.service

import com.fitnest.cookie.CookiesStorage
import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.domain.service.NetworkService
import com.fitnest.network.Endpoints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import org.kodein.di.DI

class NetworkService(val di: DI) : NetworkService {

    private val httpClient = HttpClient {
        install(HttpCookies) {
            storage = CookiesStorage(di)
        }
        install(ContentNegotiation) {
            json(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    override suspend fun sendData(path: String, data: Any?): BaseResponse {
        val url = "${Endpoints.BASE_URL}$path"
        val httpResponse: HttpResponse = httpClient.post(url) {
            contentType(ContentType.Application.Json)
            if (data != null) {
                setBody(data)
            }
        }
        return httpResponse.body()
    }

    override suspend fun getData(path: String): BaseResponse {
        val url = "${Endpoints.BASE_URL}$path"
        return httpClient.get(url).body()
    }
}
