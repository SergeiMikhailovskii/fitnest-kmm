package com.fitnest.android.internal

import androidx.compose.material.SnackbarDefaults
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.fitnest.android.enum.SnackbarState
import com.fitnest.android.style.ErrorColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class SnackbarDelegate(
    var snackbarHostState: SnackbarHostState? = null,
    var coroutineScope: CoroutineScope? = null
) {

    private var snackbarState: SnackbarState = SnackbarState.DEFAULT

    val snackbarBackgroundColor: Color
        @Composable
        get() = when (snackbarState) {
            SnackbarState.DEFAULT -> SnackbarDefaults.backgroundColor
            SnackbarState.ERROR -> ErrorColor
        }

    fun showSnackbar(
        state: SnackbarState,
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        this.snackbarState = state
        coroutineScope?.launch {
            snackbarHostState?.showSnackbar(message, actionLabel, duration)
        }
    }
}
