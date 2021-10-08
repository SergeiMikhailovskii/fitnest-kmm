package com.mikhailovskii.kmmtest.android.view.ui_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
fun GradientButton(
    gradient: Brush,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    size: Dp? = null,
    onClick: () -> Unit = { },
    body: @Composable () -> Unit
) {
    val buttonModifier = Modifier
        .width(IntrinsicSize.Min)
        .height(IntrinsicSize.Min)
        .then(modifier)
        .run {
            if (size != null) {
                size(size)
            } else {
                this
            }
        }
    Button(
        modifier = buttonModifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
        shape = shape
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .fillMaxSize()
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            body()
        }
    }
}