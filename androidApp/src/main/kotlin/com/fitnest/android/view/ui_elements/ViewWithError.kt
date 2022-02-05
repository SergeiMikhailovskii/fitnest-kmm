package com.fitnest.android.view.ui_elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fitnest.android.extension.stringResourceByIdentifier
import com.fitnest.android.style.ErrorStyle

@Composable
fun ViewWithError(
    modifier: Modifier = Modifier,
    error: String? = null,
    View: @Composable () -> Unit
) {
    Column(modifier = modifier) {
        View()
        if (error != null) {
            Box(Modifier.height(4.dp))
            Text(stringResourceByIdentifier(error), style = ErrorStyle)
        }
    }
}