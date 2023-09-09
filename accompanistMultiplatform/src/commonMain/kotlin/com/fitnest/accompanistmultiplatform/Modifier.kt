package com.fitnest.accompanistmultiplatform

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

expect fun Modifier.placeholder(
    visible: Boolean,
    color: Color = Color.Unspecified,
    shape: Shape? = null,
    highlight: PlaceholderHighlightPlatform? = null,
    placeholderFadeTransitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float> = { spring() },
    contentFadeTransitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float> = { spring() }
): Modifier

expect interface PlaceholderHighlightPlatform {
    companion object
}

expect interface PlaceholderHighlightMultiplatform {
    companion object
}

expect object PlaceholderDefaults {
    val fadeAnimationSpec: InfiniteRepeatableSpec<Float>
}

@Composable
expect fun PlaceholderHighlightMultiplatform.Companion.fade(
    animationSpec: InfiniteRepeatableSpec<Float> = PlaceholderDefaults.fadeAnimationSpec
): PlaceholderHighlightPlatform

@Composable
expect fun PlaceholderHighlightMultiplatform.Companion.fade(
    highlightColor: Color,
    animationSpec: InfiniteRepeatableSpec<Float> = PlaceholderDefaults.fadeAnimationSpec
): PlaceholderHighlightPlatform
