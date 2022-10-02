package com.fitnest.domain.exception

import com.fitnest.domain.functional.Failure

interface ExceptionHandler {
    fun getError(throwable: Throwable): Failure
}
