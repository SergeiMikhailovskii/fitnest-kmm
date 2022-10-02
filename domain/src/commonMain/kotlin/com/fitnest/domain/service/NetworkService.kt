package com.fitnest.domain.service

import com.fitnest.domain.entity.base.BaseResponse

interface NetworkService {

    suspend fun <Request> sendData(
        path: String,
        data: Request? = null
    ): BaseResponse

    suspend fun getData(path: String): BaseResponse
}
