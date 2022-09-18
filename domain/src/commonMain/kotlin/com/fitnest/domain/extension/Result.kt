package com.fitnest.domain.extension

inline fun <O, I> Result<I>.flatMap(block: (I) -> Result<O>) =
    fold(onSuccess = { block(it) }, onFailure = { Result.failure(it) })
