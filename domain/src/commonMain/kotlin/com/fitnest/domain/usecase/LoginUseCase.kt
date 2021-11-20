package com.fitnest.domain.usecase

import com.fitnest.domain.entity.LoginData
import com.fitnest.domain.functional.Either
import com.fitnest.domain.functional.Failure
import com.fitnest.domain.repository.NetworkRepository
import org.kodein.di.DI
import org.kodein.di.instance

class LoginUseCase(val di: DI) : UseCaseParams<Any, LoginData>() {

    private val repository: NetworkRepository by di.instance()

    override suspend fun run(params: LoginData): Either<Failure, Any> {
        val result = repository.loginUser(params)
        return Either.Right(Any())
    }

}