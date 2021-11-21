package com.fitnest.android.view.ui_elements

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fitnest.android.R
import com.fitnest.android.style.BrandColor
import com.fitnest.android.style.BrandGradient
import kotlinx.coroutines.delay

@Preview
@Composable
fun GradientButtonWithPreviewPreview() {
    GradientButtonWithProgress(
        gradient = Brush.horizontalGradient(BrandGradient),
        size = 50.dp,
        progress = 0.5F
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_onboarding_arrow_right),
            contentDescription = null
        )
    }
}

@Composable
fun GradientButtonWithProgress(
    gradient: Brush,
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    size: Dp? = null,
    previousProgress: Float = 0F,
    progress: Float = 0F,
    onClick: () -> Unit = { },
    body: @Composable () -> Unit
) {
    var localProgress by remember { mutableStateOf(previousProgress) }
    val animatedProgress by animateFloatAsState(
        targetValue = localProgress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    LaunchedEffect(key1 = null) {
        delay(100)
        localProgress = progress
    }
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
            progress = animatedProgress,
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