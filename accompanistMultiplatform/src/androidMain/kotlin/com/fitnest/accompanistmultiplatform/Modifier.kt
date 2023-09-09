package com.fitnest.accompanistmultiplatform

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.Transition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder as accompanistPlaceholder

actual fun Modifier.placeholder(
    visible: Boolean,
    color: Color,
    shape: Shape?,
    highlight: PlaceholderHighlightPlatform?,
    placeholderFadeTransitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float>,
    contentFadeTransitionSpec: @Composable Transition.Segment<Boolean>.() -> FiniteAnimationSpec<Float>
): Modifier =
    accompanistPlaceholder(visible, color, shape, highlight, placeholderFadeTransitionSpec, contentFadeTransitionSpec)

actual typealias PlaceholderHighlightPlatform = com.google.accompanist.placeholder.PlaceholderHighlight

actual typealias PlaceholderDefaults = com.google.accompanist.placeholder.PlaceholderDefaults

@Composable
actual fun PlaceholderHighlightMultiplatform.Companion.fade(
    animationSpec: InfiniteRepeatableSpec<Float>
): PlaceholderHighlightPlatform = com.google.accompanist.placeholder.PlaceholderHighlight.fade(animationSpec)

@Composable
actual fun PlaceholderHighlightMultiplatform.Companion.fade(
    highlightColor: Color,
    animationSpec: InfiniteRepeatableSpec<Float>
): PlaceholderHighlightPlatform = com.google.accompanist.placeholder.PlaceholderHighlight.fade(animationSpec)

actual interface PlaceholderHighlightMultiplatform {
    actual companion object
}
