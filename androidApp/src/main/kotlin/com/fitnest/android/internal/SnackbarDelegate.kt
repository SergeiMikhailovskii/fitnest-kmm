package com.fitnest.android.internal

import androidx.compose.material.ScaffoldState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class SnackbarDelegate(
    var scaffoldState: ScaffoldState? = null,
    var coroutineScope: CoroutineScope? = null
) {

    fun showSnackbar() {
        coroutineScope?.launch {
            scaffoldState?.snackbarHostState?.showSnackbar("test")
        }
    }

}
