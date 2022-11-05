package com.fitnest.android.internal

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.fitnest.android.enum.SnackbarState
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
            SnackbarState.DEFAULT -> SnackbarDefaults.color
            SnackbarState.ERROR -> MaterialTheme.colorScheme.error
        }

    fun showSnackbar(
        state: SnackbarState,
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        this.snackbarState = state
        coroutineScope?.launch {
            snackbarHostState?.showSnackbar(message, actionLabel, withDismissAction, duration)
        }
    }
}
