package com.mikhailovskii.kmmtest.usecase

import com.mikhailovskii.kmmtest.Either
import com.mikhailovskii.kmmtest.Failure
import com.mikhailovskii.kmmtest.entity.LoginData
import com.mikhailovskii.kmmtest.service.ApiService

class LoginUseCase : UseCaseParams<Any, LoginData>() {

    override suspend fun run(params: LoginData): Either<Failure, Any> {
        val result = ApiService.loginUser(params)
        return Either.Right(Any())
    }

}