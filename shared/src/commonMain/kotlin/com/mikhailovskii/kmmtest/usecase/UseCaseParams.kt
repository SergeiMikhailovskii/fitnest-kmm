package com.mikhailovskii.kmmtest.usecase

import com.mikhailovskii.kmmtest.Either
import com.mikhailovskii.kmmtest.Failure
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