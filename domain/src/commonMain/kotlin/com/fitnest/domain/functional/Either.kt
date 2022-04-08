package com.fitnest.domain.functional

sealed class Either<out L, out R> {

    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R?) : Either<Nothing, R>()

    fun either(fnL: (L) -> Any, fnR: (R?) -> Unit) {
        when (this) {
            is Left -> fnL(a)
            is Right -> fnR(b)
        }
    }

    fun doOnSuccess(onSuccess: (R?) -> Unit): Either<L, R> {
        if (this is Right) {
            onSuccess(b)
        }
        return this
    }

    fun <To> map(map: (R?) -> To): Either<L, To> {
        return if (this is Right) {
            Right(map(b))
        } else this as Left
    }
}

suspend fun <T, L, R> Either<L, R>.flatMap(fn: suspend (R?) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Either.Left -> Either.Left(a)
        is Either.Right -> fn(b)
    }