package com.fitnest.domain.service

import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.domain.functional.Either
import com.fitnest.domain.functional.Failure

interface NetworkService {

    suspend fun <Request> sendData(
        path: String,
        data: Request? = null
    ): Either<Failure, BaseResponse>

    suspend fun getData(path: String): Either<Failure, BaseResponse>

    suspend fun getDataResult(path: String): BaseResponse

}