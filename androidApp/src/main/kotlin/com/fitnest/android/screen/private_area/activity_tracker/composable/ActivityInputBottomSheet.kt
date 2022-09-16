package com.fitnest.android.screen.private_area.activity_tracker.composable

import android.widget.NumberPicker
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.fitnest.android.R
import com.fitnest.android.extension.enum.fromLocalizedName
import com.fitnest.android.extension.enum.localizedNames
import com.fitnest.android.style.Dimen
import com.fitnest.android.style.GrayColor1
import com.fitnest.android.style.Padding
import com.fitnest.android.style.PoppinsBoldStyle16
import com.fitnest.android.style.PoppinsSemiBoldStyle14White
import com.fitnest.domain.enum.ActivityType

@Preview
@Composable
internal fun ActivityInputBottomSheetPreview() {
    ActivityInputBottomSheet { _, _ -> }
}

@Composable
internal fun ActivityInputBottomSheet(onSubmit: (ActivityType, Int) -> Unit) {
    val context = LocalContext.current
    val items = ActivityType.localizedNames(context)
    var currentActive by remember {
        mutableStateOf(items[0])
    }
    var picker: NumberPicker? = null

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(top = Padding.Padding8)
                .width(Dimen.Dimen60)
                .height(Dimen.Dimen8)
                .clip(CircleShape)
                .background(GrayColor1)
        )
        Row(
            modifier = Modifier
                .padding(top = Padding.Padding24)
                .fillMaxWidth()
                .padding(horizontal = Padding.Padding30)
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
                        .weight(1F)
                        .background(background)
                        .clickable {
                            currentActive = it
                        }
                        .padding(
                            vertical = Padding.Padding8,
                            horizontal = Padding.Padding16
                        ),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = PoppinsSemiBoldStyle14White
                )
            }
        }
        AndroidView(
            modifier = Modifier
                .padding(top = Padding.Padding20)
                .padding(horizontal = Padding.Padding30)
                .fillMaxWidth(),
            factory = {
                picker = NumberPicker(it).apply {
                    this.minValue = 0
                    this.maxValue = 10
                    this.value = 0
                    setFormatter {
                        it.times(100).toString()
                    }
                    descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
                    wrapSelectorWheel = true
                }
                picker!!
            }
        )
        Button(
            onClick = {
                val activityType = ActivityType.fromLocalizedName(
                    currentActive,
                    context
                )
                onSubmit(activityType, (picker?.value ?: 0) * 100)
            },
            shape = CircleShape,
            modifier = Modifier
                .padding(all = Padding.Padding30)
                .height(Dimen.Dimen60)
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.private_area_activity_tracker_screen_latest_activity_save),
                style = PoppinsBoldStyle16
            )
        }
    }
}
