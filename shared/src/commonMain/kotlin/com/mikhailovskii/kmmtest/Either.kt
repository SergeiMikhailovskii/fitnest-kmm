package com.mikhailovskii.kmmtest

sealed class Either<out L, out R> {

    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    fun either(fnL: (L) -> Any, fnR: (R) -> Unit) {
        when (this) {
            is Left -> fnL(a)
            is Right -> fnR(b)
        }
    }

}