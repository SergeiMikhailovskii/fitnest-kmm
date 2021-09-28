package com.mikhailovskii.kmmtest.usecase

import com.mikhailovskii.kmmtest.Either
import com.mikhailovskii.kmmtest.Failure
import com.mikhailovskii.kmmtest.entity.LoginData
import com.mikhailovskii.kmmtest.service.Repository
import org.kodein.di.DI

class LoginUseCase(val di: DI) : UseCaseParams<Any, LoginData>() {

    override suspend fun run(params: LoginData): Either<Failure, Any> {
        val result = Repository(di).loginUser(params)
        return Either.Right(Any())
    }

}