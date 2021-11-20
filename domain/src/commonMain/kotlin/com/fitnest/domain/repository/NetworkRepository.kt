package com.fitnest.domain.repository

import com.fitnest.domain.entity.LoginData
import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.domain.functional.Either
import com.fitnest.domain.functional.Failure

interface NetworkRepository {

    suspend fun loginUser(data: LoginData)

    suspend fun generateToken(): Either<Failure, BaseResponse>

    suspend fun getOnboardingStep(): Either<Failure, String>

}