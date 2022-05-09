package com.fitnest.android.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Int.pxToDp(): Dp {
    val density = LocalContext.current.resources.displayMetrics.density
    return (this / density).dp
}