package com.fitnest.presentation.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
actual fun Int.pxToDp(): Dp {
    // need to find a solution. now it is just a stub
    return this.dp
}
