package com.fitnest.android.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun stringResourceByIdentifier(identifier: String): String {
    println(identifier)
    val context = LocalContext.current
    return context.getString(
        context.resources.getIdentifier(
            identifier,
            "string",
            context.packageName
        )
    )
}
