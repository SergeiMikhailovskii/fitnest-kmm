package com.fitnest.service

import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.domain.functional.Either
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.service.NetworkService
import com.fitnest.cookie.CookiesStorage
import com.fitnest.network.Endpoints
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
import kotlinx.serialization.json.Json
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

    override suspend fun <Request> sendData(
        path: String,
        data: Request?
    ): Either<Failure, BaseResponse> {
        val url = "${Endpoints.BASE_URL}${path}"
        val httpResponse: HttpResponse = httpClient.post(url) {
            contentType(ContentType.Application.Json)
            if (data != null) {
                body = data
            }
        }
        return Either.Right(httpResponse.receive())
    }

    override suspend fun getData(path: String): Either<Failure, BaseResponse> {
        val url = "${Endpoints.BASE_URL}${path}"
        return try {
            val httpResponse: HttpResponse = httpClient.get(url) {
                contentType(ContentType.Application.Json)
            }
            val response = httpResponse.receive<BaseResponse>()
            Either.Right(response)
        } catch (e: ClientRequestException) {
            Either.Left(Failure.ServerError(e.response.status.value))
        }
    }
}