package com.fitnest.android.screen.private_area.activity_tracker.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.fitnest.android.style.Padding

@Composable
internal fun ActivityInputBottomSheet() {
    val items = listOf("Calories", "Steps")
    var currentActive by remember {
        mutableStateOf(items[0])
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Red),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach {
                val background by animateColorAsState(
                    targetValue = if (it == currentActive) Color.Blue else Color.Transparent,
                    animationSpec = tween(AnimationConstants.DefaultDurationMillis)
                )
                Text(
                    it,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(background)
                        .clickable {
                            currentActive = it
                        }
                        .padding(
                            vertical = Padding.Padding8,
                            horizontal = Padding.Padding16
                        ),
                    color = Color.White,
                )
            }
        }
    }
}
