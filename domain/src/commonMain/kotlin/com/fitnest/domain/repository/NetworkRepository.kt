package com.fitnest.domain.repository

import com.fitnest.domain.entity.GetRegistrationResponseData
import com.fitnest.domain.entity.LoginData
import com.fitnest.domain.entity.base.BaseRequest
import com.fitnest.domain.entity.base.BaseResponse
import com.fitnest.domain.functional.Either
import com.fitnest.domain.functional.Failure

interface NetworkRepository {

    suspend fun loginUser(data: LoginData)

    suspend fun generateToken(): Either<Failure, BaseResponse>

    suspend fun getOnboardingStep(): Either<Failure, String>

    suspend fun submitOnboardingStep(): Either<Failure, Unit>

    suspend fun getRegistrationStepData(): Either<Failure, GetRegistrationResponseData>

    suspend fun submitRegistrationStep(request: BaseRequest): Either<Failure, Unit>

    suspend fun getDashboardData(): BaseResponse

}