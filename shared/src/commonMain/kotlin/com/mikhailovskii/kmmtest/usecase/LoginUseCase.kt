package com.mikhailovskii.kmmtest.usecase

import com.mikhailovskii.kmmtest.Either
import com.mikhailovskii.kmmtest.Failure
import com.mikhailovskii.kmmtest.entity.LoginData
import com.mikhailovskii.kmmtest.repository.LocalStorageRepository

class LoginUseCase : UseCaseParams<Any, LoginData>() {

    private val localStorageRepository: LocalStorageRepository = LocalStorageRepository()

    override suspend fun run(params: LoginData): Either<Failure, Any> {
        localStorageRepository.saveValue("login", params.login)
        localStorageRepository.saveValue("password", params.password)
        return Either.Right(Any())
    }

}