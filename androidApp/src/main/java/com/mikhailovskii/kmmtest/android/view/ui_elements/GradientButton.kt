package com.mikhailovskii.kmmtest.android.view.ui_elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mikhailovskii.kmmtest.android.R
import com.mikhailovskii.kmmtest.android.style.BrandColor
import com.mikhailovskii.kmmtest.android.style.BrandGradient

@Preview
@Composable
fun GradientButtonPreview() {
    GradientButton(
        gradient = Brush.horizontalGradient(BrandGradient),
        size = 50.dp,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_onboarding_arrow_right),
            contentDescription = null
        )
    }
}

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
    Box(
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxSize(),
            progress = 0.25F,
            strokeWidth = 2.dp,
            color = BrandColor
        )
        Button(
            modifier = Modifier
                .padding(10.dp)
                .then(buttonModifier),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            contentPadding = PaddingValues(),
            onClick = { onClick() },
            shape = shape
        ) {
            Box(
                modifier = Modifier
                    .background(brush = gradient)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                body()
            }
        }
    }
}