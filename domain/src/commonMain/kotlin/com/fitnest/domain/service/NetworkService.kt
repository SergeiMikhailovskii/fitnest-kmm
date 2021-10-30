package com.fitnest.domain.service

import com.fitnest.domain.functional.Either
import com.fitnest.domain.functional.Failure

interface NetworkService {

    suspend fun <Request, Response> sendData(url: String, data: Request): Response

    suspend fun <Response> fetchData(path: String): Either<Failure, Response>

}