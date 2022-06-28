package com.fitnest.android.extension

import android.content.res.Resources
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
internal fun stringResourceByIdentifier(identifier: String): String {
    val context = LocalContext.current
    return try {
        context.getString(
            context.resources.getIdentifier(
                identifier,
                "string",
                context.packageName
            )
        )
    } catch (e: Resources.NotFoundException) {
        identifier
    }
}

internal fun vibrate(view: View) {
    view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
}