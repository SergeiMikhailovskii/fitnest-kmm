package com.fitnest.domain.service

import com.fitnest.domain.entity.base.BaseResponse

interface NetworkService {

    suspend fun sendData(path: String, data: Any? = null): BaseResponse

    suspend fun getData(path: String): BaseResponse
}
