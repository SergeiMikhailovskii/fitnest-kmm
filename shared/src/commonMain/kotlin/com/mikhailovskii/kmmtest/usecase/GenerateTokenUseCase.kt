package com.mikhailovskii.kmmtest.usecase

import com.mikhailovskii.kmmtest.Either
import com.mikhailovskii.kmmtest.Failure
import com.mikhailovskii.kmmtest.entity.LoginData
import com.mikhailovskii.kmmtest.service.Repository
import org.kodein.di.DI
import org.kodein.di.instance

class GenerateTokenUseCase(val di: DI) : UseCase<Any>() {

    private val repository: Repository by di.instance()

    override suspend fun run(): Either<Failure, Any> {
        val result = repository.generateToken()
        return Either.Right(Any())
    }

}