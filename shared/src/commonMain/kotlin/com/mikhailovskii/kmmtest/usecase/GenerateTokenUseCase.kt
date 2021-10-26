package com.mikhailovskii.kmmtest.usecase

import com.mikhailovskii.kmmtest.Either
import com.mikhailovskii.kmmtest.Failure
import com.mikhailovskii.kmmtest.repository.NetworkRepository
import org.kodein.di.DI
import org.kodein.di.instance

class GenerateTokenUseCase(val di: DI) : UseCase<Any>() {

    private val repository: NetworkRepository by di.instance()

    override suspend fun run(): Either<Failure, Any> {
        return repository.generateToken()
    }

}