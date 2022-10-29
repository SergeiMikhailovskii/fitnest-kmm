package com.fitnest.android.view.ui_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fitnest.android.style.GrayColor3

@Composable
fun DividerWithChild(modifier: Modifier, child: @Composable () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .weight(1F)
                .height(1.dp)
                .background(GrayColor3)
        )
        child()
        Box(
            modifier = Modifier
                .weight(1F)
                .height(1.dp)
                .background(GrayColor3)
        )
    }
}