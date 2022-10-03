package com.fitnest.domain.extension

inline fun <O, I> Result<I>.flatMap(block: (I) -> Result<O>) =
    fold(onSuccess = { block(it) }, onFailure = { Result.failure(it) })

inline fun <O : Throwable, T> Result<T>.mapError(block: (Throwable) -> O) =
    fold(onSuccess = { Result.success(it) }, onFailure = { Result.failure(block(it)) })
