package com.fitnest.presentation.internal

import com.fitnest.domain.functional.Failure

interface ErrorHandlerDelegate {
    fun defaultHandleFailure(failure: Failure)
}
