package com.fitnest.android.internal

import com.fitnest.domain.functional.Failure

internal class ErrorHandlerDelegate(
    private val snackbarService: SnackbarDelegate
) {

    fun defaultHandleFailure(failure: Failure) {
        when (failure) {
            is Failure.ServerError -> snackbarService.showSnackbar()
            else -> {}
        }
    }

}
