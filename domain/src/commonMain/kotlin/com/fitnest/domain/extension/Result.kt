package com.fitnest.domain.extension

suspend fun <O, I> Result<I>.flatMap(block: suspend (I) -> Result<O>) =
    fold(onSuccess = { block(it) }, onFailure = Result.Companion::failure)
