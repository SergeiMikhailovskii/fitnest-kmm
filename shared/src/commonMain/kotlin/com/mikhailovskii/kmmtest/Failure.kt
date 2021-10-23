package com.mikhailovskii.kmmtest

sealed class Failure {
    data class ServerError(val responseCode: Int) : Failure()
}
