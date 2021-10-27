package com.fitnest.domain.functional

sealed class Failure {
    data class ServerError(val responseCode: Int) : Failure()
}
