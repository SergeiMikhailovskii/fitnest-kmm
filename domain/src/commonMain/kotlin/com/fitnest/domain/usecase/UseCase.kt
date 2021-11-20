package com.fitnest.domain.usecase

import com.fitnest.domain.functional.Either
import com.fitnest.domain.functional.Failure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<out Type> {

    abstract suspend fun run(): Either<Failure, Type>

    operator fun invoke(onResult: (Either<Failure, Type>) -> Unit) {
        val job = CoroutineScope(Dispatchers.Default).async { run() }
        CoroutineScope(Dispatchers.Main).launch {
            onResult(job.await())
        }
    }

}

abstract class UseCaseUnit : UseCase<Unit>()