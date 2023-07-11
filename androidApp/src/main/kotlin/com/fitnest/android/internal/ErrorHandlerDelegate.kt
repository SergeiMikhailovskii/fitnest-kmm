package com.fitnest.android.internal

import android.content.Context
import com.fitnest.android.R
import com.fitnest.android.enum.SnackbarState
import com.fitnest.domain.functional.Failure
import com.fitnest.presentation.internal.ErrorHandlerDelegate

internal class ErrorHandlerDelegate(
    private val context: Context,
    private val snackbarService: SnackbarDelegate
) : ErrorHandlerDelegate {

    override fun defaultHandleFailure(failure: Failure) {
        when (failure) {
            is Failure.ServerError,
            is Failure.Unknown -> snackbarService.showSnackbar(
                SnackbarState.ERROR,
                context.getString(R.string.error_server_error),
                testTag = "snackbarError"
            )

            else -> {}
        }
    }
}
