package com.mikhailovskii.kmmtest.usecase

import com.fitnest.domain.entity.LoginData
import com.mikhailovskii.kmmtest.Either
import com.mikhailovskii.kmmtest.Failure
import com.mikhailovskii.kmmtest.repository.NetworkRepository
import org.kodein.di.DI
import org.kodein.di.instance

class LoginUseCase(val di: DI) : UseCaseParams<Any, LoginData>() {

    private val repository: NetworkRepository by di.instance()

    override suspend fun run(params: LoginData): Either<Failure, Any> {
        val result = repository.loginUser(params)
        return Either.Right(Any())
    }

}