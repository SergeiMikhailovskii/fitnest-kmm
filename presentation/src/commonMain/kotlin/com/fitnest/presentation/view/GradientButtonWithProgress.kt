package com.fitnest.presentation.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ButtonWithProgress(
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
        )
        Button(
            modifier = Modifier
                .padding(10.dp)
                .then(buttonModifier),
            contentPadding = PaddingValues(),
            onClick = { onClick() },
            shape = shape
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                body()
            }
        }
    }
}