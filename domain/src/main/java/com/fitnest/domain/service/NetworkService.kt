package com.fitnest.domain.service

import com.fitnest.domain.functional.Either
import com.fitnest.domain.functional.Failure
import io.ktor.client.statement.*

interface NetworkService {

    suspend fun <T : Any> sendData(url: String, data: T): HttpResponse

    suspend fun fetchData(path: String): Either<Failure, String>

}