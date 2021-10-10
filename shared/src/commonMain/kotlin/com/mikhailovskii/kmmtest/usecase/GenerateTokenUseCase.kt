package com.mikhailovskii.kmmtest.usecase

import com.mikhailovskii.kmmtest.Either
import com.mikhailovskii.kmmtest.Failure
import com.mikhailovskii.kmmtest.entity.LoginData
import com.mikhailovskii.kmmtest.service.Repository
import org.kodein.di.DI

class GenerateTokenUseCase(val di: DI) : UseCase<Any>() {

    override suspend fun run(): Either<Failure, Any> {
        val result = Repository(di).generateToken()
        return Either.Right(Any())
    }

}