package com.fitnest.domain.service

import com.fitnest.domain.entity.base.BaseResponse

interface NetworkService {

    suspend fun <Request> sendDataResult(
        path: String,
        data: Request? = null
    ): BaseResponse

    suspend fun getDataResult(path: String): BaseResponse
}
