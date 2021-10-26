package com.mikhailovskii.kmmtest.usecase

import com.fitnest.domain.functional.Either
import com.fitnest.domain.functional.Failure
import kotlinx.coroutines.*

abstract class UseCaseParams<out Type, in Params> {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit) {
        val job = CoroutineScope(Dispatchers.Default).async { run(params) }
        CoroutineScope(Dispatchers.Main).launch {
            onResult(job.await())
        }
    }

}